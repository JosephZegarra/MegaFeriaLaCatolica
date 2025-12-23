package com.alexander.curso.spring.boot.webapp.springboot_web.mapper;

import com.alexander.curso.spring.boot.webapp.springboot_web.dto.FamiliaresDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.dto.SocioDTO;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.FamiliaresEntity;
import com.alexander.curso.spring.boot.webapp.springboot_web.entity.SocioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//Ok Identifique el error era que no me dejaba guardar porque en mi en mmi family entity yo estoy poeniendo un objeto abajo de
//join columm que Socioentity socio y esta esperando un objeto ,pero yo le estaba pasando un ID socio y por eso aria erro en el
//mapper porque convierto de dto a entity y como no encontraba un valor en idsocio lo marcaba como null con este codigo estoy extraendo el id y creeando
//un objeto que se necesita para convertir de dto a entity
@Mapper(componentModel = "spring")
public interface FamiliarMapper
{
    // "source" indica el campo de origen, y "target" el campo de destino.
    // En este caso: del objeto "socio" dentro de la entidad, toma su "idsocio"
    // y lo asigna al campo "idsocio" del DTO.

    //es que lo que pasa en que en mi socio DTO tego el idsocio no el objeto socio y como el entity tiene un objeto al convertir a DTO
    //sale null por dto debes del tener el objeto socio movimiewntoDTO tiene idmovimiento
    //le digo que cada vez que tengas el idmovimiento relacionalo a un obejto socio y asignalo a el para que haga la trnasformación de enity a DTO
    @Mapping(source = "socio.idsocio", target = "idsocio")
    FamiliaresDTO FamiliaresEntityAFamiliaresDTO(FamiliaresEntity familiaresEntity);

    @Mapping(source = "idsocio", target = "socio")
    FamiliaresEntity FamiliaresDTOAFamiliaresEntity(FamiliaresDTO familiaresDTO);

    // 🔹 Este método convierte el ID del socio en una entidad SocioEntity
    default SocioEntity map(Integer idsocio) {
        if (idsocio == null) {
            return null;
        }
        SocioEntity socio = new SocioEntity();
        socio.setIdsocio(idsocio);
        return socio;
    }

}
