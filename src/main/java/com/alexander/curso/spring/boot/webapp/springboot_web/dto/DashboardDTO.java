package com.alexander.curso.spring.boot.webapp.springboot_web.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DashboardDTO
{   /*
    private Long sociosInactivos;
    private Long sociosBloqueados;

    // KPI Metrics (Current Month)
    private Long totalAsistenciasMes;
    private Long totalAusenciasJustificadas;
    private Long totalAusenciasInjustificadas;
    private Long totalTardanzas;

    // Chart Data
    private List<DatosTendenciaDTO> tendenciaAsistencia;
    private List<DatosRubroDTO> asistenciaPorRubro;

    // Alerts
    private List<SocioAlertaDTO> sociosLimiteAusencias;
    private Long justificacionesPendientes;

    // Top Performers
    private List<MejorSocioDTO> mejoresPorCategoria;

    // Top debtors
    //private List<DeudorDTO> topDeudores;

    // Inner class for attendance trend data (chart)
    public static class DatosTendenciaDTO {
        private LocalDate fecha;
        private Long cantidad;

        public DatosTendenciaDTO() {
        }

        public DatosTendenciaDTO(LocalDate fecha, Long cantidad) {
            this.fecha = fecha;
            this.cantidad = cantidad;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public void setFecha(LocalDate fecha) {
            this.fecha = fecha;
        }

        public Long getCantidad() {
            return cantidad;
        }

        public void setCantidad(Long cantidad) {
            this.cantidad = cantidad;
        }
    }

    // Inner class for attendance by category data (chart)
    public static class DatosRubroDTO {
        private String rubro;
        private Long cantidad;

        public DatosRubroDTO() {
        }

        public DatosRubroDTO(String rubro, Long cantidad) {
            this.rubro = rubro;
            this.cantidad = cantidad;
        }

        public String getRubro() {
            return rubro;
        }

        public void setRubro(String rubro) {
            this.rubro = rubro;
        }

        public Long getCantidad() {
            return cantidad;
        }

        public void setCantidad(Long cantidad) {
            this.cantidad = cantidad;
        }
    }

    // Inner class for members at absence limit (alert)
    public static class SocioAlertaDTO {
        private String nombreCompleto;
        private Long totalAusencias;

        public SocioAlertaDTO() {
        }

        public SocioAlertaDTO(String nombreCompleto, Long totalAusencias) {
            this.nombreCompleto = nombreCompleto;
            this.totalAusencias = totalAusencias;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public Long getTotalAusencias() {
            return totalAusencias;
        }

        public void setTotalAusencias(Long totalAusencias) {
            this.totalAusencias = totalAusencias;
        }
    }

    // Inner class for best performer by category
    public static class MejorSocioDTO {
        private String nombreCompleto;
        private String categoria;
        private Double porcentajeAsistencia;

        public MejorSocioDTO() {
        }

        public MejorSocioDTO(String nombreCompleto, String categoria, Double porcentajeAsistencia) {
            this.nombreCompleto = nombreCompleto;
            this.categoria = categoria;
            this.porcentajeAsistencia = porcentajeAsistencia;
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Double getPorcentajeAsistencia() {
            return porcentajeAsistencia;
        }

        public void setPorcentajeAsistencia(Double porcentajeAsistencia) {
            this.porcentajeAsistencia = porcentajeAsistencia;
        }
    }
    /*
    // Inner class for debtor information
    public static class DeudorDTO {
        private Integer idsocio;
        @@ -120,4 +268,77 @@ public List<DeudorDTO> getTopDeudores() {
            public void setTopDeudores(List<DeudorDTO> topDeudores) {
                this.topDeudores = topDeudores;
            }

            // Getters and Setters for new fields
            public Long getTotalAsistenciasMes() {
                return totalAsistenciasMes;
            }

            public void setTotalAsistenciasMes(Long totalAsistenciasMes) {
                this.totalAsistenciasMes = totalAsistenciasMes;
            }

            public Long getTotalAusenciasJustificadas() {
                return totalAusenciasJustificadas;
            }

            public void setTotalAusenciasJustificadas(Long totalAusenciasJustificadas) {
                this.totalAusenciasJustificadas = totalAusenciasJustificadas;
            }

            public Long getTotalAusenciasInjustificadas() {
                return totalAusenciasInjustificadas;
            }

            public void setTotalAusenciasInjustificadas(Long totalAusenciasInjustificadas) {
                this.totalAusenciasInjustificadas = totalAusenciasInjustificadas;
            }

            public Long getTotalTardanzas() {
                return totalTardanzas;
            }

            public void setTotalTardanzas(Long totalTardanzas) {
                this.totalTardanzas = totalTardanzas;
            }

            public List<DatosTendenciaDTO> getTendenciaAsistencia() {
                return tendenciaAsistencia;
            }

            public void setTendenciaAsistencia(List<DatosTendenciaDTO> tendenciaAsistencia) {
                this.tendenciaAsistencia = tendenciaAsistencia;
            }

            public List<DatosRubroDTO> getAsistenciaPorRubro() {
                return asistenciaPorRubro;
            }

            public void setAsistenciaPorRubro(List<DatosRubroDTO> asistenciaPorRubro) {
                this.asistenciaPorRubro = asistenciaPorRubro;
            }

            public List<SocioAlertaDTO> getSociosLimiteAusencias() {
                return sociosLimiteAusencias;
            }

            public void setSociosLimiteAusencias(List<SocioAlertaDTO> sociosLimiteAusencias) {
                this.sociosLimiteAusencias = sociosLimiteAusencias;
            }

            public Long getJustificacionesPendientes() {
                return justificacionesPendientes;
            }

            public void setJustificacionesPendientes(Long justificacionesPendientes) {
                this.justificacionesPendientes = justificacionesPendientes;
            }

            public List<MejorSocioDTO> getMejoresPorCategoria() {
                return mejoresPorCategoria;
            }

            public void setMejoresPorCategoria(List<MejorSocioDTO> mejoresPorCategoria) {
                this.mejoresPorCategoria = mejoresPorCategoria;
            }
            */


}
