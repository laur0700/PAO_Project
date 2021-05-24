import java.util.Date;

public class Distribuitor
{
    private String nume;
    private Boolean statusContract;
    private Date contractStart;
    private Date contractEnd;

    public void setNume(String Nume)
    {
        this.nume = Nume;
    }

    public void setStatusContract(Boolean StatusContract)
    {
        this.statusContract = StatusContract;
    }

    public void setContractStart(Date ContractStart)
    {
        this.contractStart = ContractStart;
    }

    public void setContractEnd(Date ContractEnd)
    {
        this.contractEnd = ContractEnd;
    }

    public String getNume()
    {
        return this.nume;
    }

    public Boolean getStatusContract()
    {
        return this.statusContract;
    }

    public Date getContractStart()
    {
        return this.contractStart;
    }

    public Date getContractEnd()
    {
        return this.contractEnd;
    }
}
