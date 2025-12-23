package com.alexander.curso.spring.boot.webapp.springboot_web.service.Impl;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.mapper.SocioMapper;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.repository.SocioRolRepository;
import com.alexander.curso.spring.boot.webapp.springboot_web.security.JwtUtil;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.CorreoService;
import com.alexander.curso.spring.boot.webapp.springboot_web.service.SocioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service //La clase que tiene la lógica del negocio
public class SocioServiceImpl  implements SocioService
{    //PARA MOSTRAR al usuario lo convierto a DTO
    //para guardar en la base de datos en entity
    @Autowired
    SocioRepository socioRepository;//acciones en la base de datos

    @Autowired
    SocioMapper socioMapper;

    @Autowired
    SocioRolRepository socioRolRepository;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@Autowired
    //private JwtUtil jwtUtil;

    @Override
    public List<SocioDTO>listar()
    {
        //Este metodo lo convierte a DTO
        //return SocioMapper.INSTANCE.socioDTOASocioDTO(socioRepository.findAll());//convierte de lista de socios entity ha lista de socios DTO
        return socioRepository.findByEstado("Activo")
                .stream()
                .map(s -> new SocioDTO(s.getIdsocio(),s.getNombresocio(),s.getApellidopaternosocio(),s.getDireccion(),s.getTelefono()))
                .toList();
    }



    @Override
    public SocioDTO BuscarPorId(Integer idsocio)
    {
       Optional<SocioEntity> socioEntity=
               Optional.ofNullable(socioRepository.findById(idsocio).orElseThrow(()
               -> new EntityNotFoundException("Socio no encontrado")));
       return socioMapper.SocioEntityASocioDTO(socioEntity.get());

    }

    @Override
    public SocioDTO guardar(SocioDTO socioDTO)
    {
        //Miramos si existe el DNI primero
        if(socioRepository.existsByDni(socioDTO.getDni()))
        {
            throw  new IllegalArgumentException("El DNI que ingreso ya está registrado. Ingrese otro DNI");
        }

        if(socioRepository.existsByNumero_carnet(socioDTO.getNumero_carnet()))
        {
            //throw  new IllegalArgumentException("El numero de carnet ya está registrado. Ingrese otro numero de carnet");
            String sugerencia = sugerirSiguienteNumeroCarnet();
            throw new IllegalArgumentException(
                    "El número de carnet ya está registrado. Puede usar el siguiente: " + sugerencia
            );
        }


        //Convertir de DTO a entity
        SocioEntity socioEntity= socioMapper.SocioDTOASocioEntity(socioDTO);

        //Encriptar la contrasena antes de guardar
        String hashedPassword=passwordEncoder.encode(socioEntity.getContrasena());
        socioEntity.setContrasena(hashedPassword);

        //Código que identifica si el correo puso el cliente para generar token automatico
        if (socioEntity.getCorreo() != null && !socioEntity.getCorreo().isEmpty()) {
            // Generar token aleatorio
            String token = UUID.randomUUID().toString();
            socioEntity.setToken(token);
            socioEntity.setCorreoVerificado(false);
        } else {
            // No tiene correo
            socioEntity.setToken(null);
            socioEntity.setCorreoVerificado(null); // o false si prefieres
        }
        //Guardo en la base de datos
        SocioEntity savedEntity =socioRepository.save(socioEntity);

        //Generar JWT para el socio (para el inicio sesión)
        //String jwtToken=jwtUtil.generateToken(savedEntity.getNumero_carnet());
        //savedEntity.setToken(jwtToken);

        //Enviar correo si es que tiene un correo valido
        if(savedEntity.getCorreo()!=null && !savedEntity.getCorreo().isEmpty())//isEmpty verifica que la cadena este vacia
        {
            try{
                String asunto="Confirmación de Registro";
                String mensaje="Hola"+" "+savedEntity.getNombresocio()+
                        ", gracias por registrarte en la Mega Feria de la Católica.\n\n" +
                        "Por favor confirma tu correo haciendo clic en el siguiente enlace:\n" +
                        "http://localhost:8080/socios/confirmarcorreo?token=" + savedEntity.getToken();
                correoService.enviarCorreo(savedEntity.getCorreo(),asunto,mensaje);
            }catch(Exception e)
            {
                System.out.println("Error al enviar correo a "+savedEntity.getCorreo()+ ": " + e.getMessage());
            }
        }

        //Enviar correo
        //correoService.enviarCorreo(savedEntity.getCorreo(),"Registro exitoso","Hola"+" "+socioEntity.getNombresocio()+", tu registro en el sistema de la Mega Feria de la Católica  fue éxitoso ");

        return socioMapper.SocioEntityASocioDTO(savedEntity);
    }

    @Override
    @Transactional
    public SocioDTO editar(SocioDTO socioDTO)
    {



        SocioEntity socioEntity=socioRepository.findById(socioDTO.getIdsocio()).orElseThrow(()
                ->new EntityNotFoundException("Socio no encontrado"));


        socioEntity.setIdsocio(socioDTO.getIdsocio());
        socioEntity.setNombresocio(socioDTO.getNombresocio());
        socioEntity.setApellidopaternosocio(socioDTO.getApellidopaternosocio());
        //socioEntity.setApellidomaternosocio(socioDTO.getApellidomaternosocio());
        socioEntity.setDireccion(socioDTO.getDireccion());
        socioEntity.setDistrito(socioDTO.getDistrito());
        //socioEntity.setEstadocivil(socioDTO.getEstadocivil());
        socioEntity.setDni(socioDTO.getDni());
        socioEntity.setNumero_carnet(socioDTO.getNumero_carnet());
        socioEntity.setTelefono(socioDTO.getTelefono());
        socioEntity.setEstado(socioDTO.getEstado());
        socioEntity.setTipo(socioDTO.getTipo());
        socioEntity.setCorreo(socioDTO.getCorreo());
        
        socioRepository.save(socioEntity);
        return socioMapper.SocioEntityASocioDTO(socioEntity);
    }




    //socioEntity.setContrasena(socioDTO.getContrasena());
    @Override
    public void borrar(Integer idsocio)
    {
        socioRepository.softDelete(idsocio);
    }

    @Override
    public SocioDTO validarSocio(String numero_carnet,String contrasena)
    {
        SocioDTO socio = BuscarPorNumero_Carnet(numero_carnet); // busca solo por carnet

        if (socio == null) {
            System.out.println("No se encontró socio con carnet: " + numero_carnet);
            return null;
        }

        System.out.println("Hash en BD: " + socio.getContrasena());
        System.out.println("Contraseña ingresada: [" + contrasena + "]");

        if (passwordEncoder.matches(contrasena, socio.getContrasena())) {
            System.out.println("Contraseña correcta");
            return socio; // contraseña correcta
        } else {
            System.out.println("Contraseña incorrecta");
            return null; // contraseña incorrecta
        }
    }
    public Integer obtenerIdSocioPorCarnet(String numero_carnet) {
        return socioRepository.obtenerIdPorNumeroCarnet(numero_carnet)
                .orElse(null);
    }

    @Override
    public boolean confirmarCorreo(String token) {
        Optional<SocioEntity> optionalSocio = socioRepository.findByToken(token);

        if (optionalSocio.isPresent()) {
            SocioEntity socio = optionalSocio.get();
            socio.setCorreoVerificado(true);
            socio.setToken(null); // El token se invalida después de confirmar
            socioRepository.save(socio);
            return true;
        } else {
            return false;
        }
    }

    /*
    @Override
    public String obtenerrolporsocio(Integer idsocio)
    {
        return socioRolRepository.EncuentraRolActivoPorSocioId(idsocio);
    }
    */
    @Override
    public SocioDTO BuscarPorNumero_Carnet(String numero_carnet)
    {
        /*
       SocioEntity socioEntity=socioRepository.findByNumeroCarnet(numero_carnet).orElseThrow(() -> new RuntimeException("No se encontró un socio con el número de carnet: " + numero_carnet));
        System.out.println(socioEntity.getCorreo());
       return socioMapper.SocioEntityASocioDTO(socioEntity);

         */
        Optional<SocioEntity> socioOpt = socioRepository.findByNumeroCarnet(numero_carnet);

        if (socioOpt.isPresent()) {
            SocioEntity socioEntity = socioOpt.get();
            System.out.println(socioEntity.getCorreo()); // depuración
            return socioMapper.SocioEntityASocioDTO(socioEntity);
        } else {
            System.out.println("No se encontró un socio con el número de carnet: " + numero_carnet);
            return null; // ahora sí puede devolver null
        }
    }


    private String sugerirSiguienteNumeroCarnet() {
        String ultimo = socioRepository.findUltimoNumeroCarnet();

        int numero = 0;
        if (ultimo != null && ultimo.startsWith("CARNET-")) {
            numero = Integer.parseInt(ultimo.replace("CARNET-", ""));
        }

        int siguiente = numero + 1;
        return String.format("CARNET-%03d", siguiente);
    }

    @Override
    public SocioEntity buscarEntidadPorNumeroCarnet(String numeroCarnet)
    {
        return socioRepository.findByNumeroCarnet(numeroCarnet)
                .orElse(null);
    }

}
