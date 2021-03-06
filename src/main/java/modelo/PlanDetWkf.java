package modelo;
// Generated 09-02-2019 10:41:42 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PlanDetWkf generated by hbm2java
 */
public class PlanDetWkf  implements java.io.Serializable {


     private Integer id;
     private EmpMae empMae;
     private PlanMaeWkf planMaeWkf;
     private TtipoMovimiento ttipoMovimiento;
     private BigDecimal impMo;
     private BigDecimal impEq;
     private String snAjustado;

    public PlanDetWkf() {
        this.planMaeWkf = new PlanMaeWkf();
        this.ttipoMovimiento = new TtipoMovimiento();
        this.impEq = BigDecimal.valueOf(0);
        this.impMo = BigDecimal.valueOf(0);
        this.snAjustado = "N";
    }

    public PlanDetWkf(EmpMae empMae, PlanMaeWkf planMaeWkf, TtipoMovimiento ttipoMovimiento, BigDecimal impMo, BigDecimal impEq, String snAjustado) {
       this.empMae = empMae;
       this.planMaeWkf = planMaeWkf;
       this.ttipoMovimiento = ttipoMovimiento;
       this.impMo = impMo;
       this.impEq = impEq;
       this.snAjustado = snAjustado;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public EmpMae getEmpMae() {
        return this.empMae;
    }
    
    public void setEmpMae(EmpMae empMae) {
        this.empMae = empMae;
    }
    public PlanMaeWkf getPlanMaeWkf() {
        return this.planMaeWkf;
    }
    
    public void setPlanMaeWkf(PlanMaeWkf planMaeWkf) {
        this.planMaeWkf = planMaeWkf;
    }
    public TtipoMovimiento getTtipoMovimiento() {
        return this.ttipoMovimiento;
    }
    
    public void setTtipoMovimiento(TtipoMovimiento ttipoMovimiento) {
        this.ttipoMovimiento = ttipoMovimiento;
    }
    public BigDecimal getImpMo() {
        return this.impMo;
    }
    
    public void setImpMo(BigDecimal impMo) {
        this.impMo = impMo;
    }
    public BigDecimal getImpEq() {
        return this.impEq;
    }
    
    public void setImpEq(BigDecimal impEq) {
        this.impEq = impEq;
    }
    public String getSnAjustado() {
        return this.snAjustado;
    }
    
    public void setSnAjustado(String snAjustado) {
        this.snAjustado = snAjustado;
    }




}


