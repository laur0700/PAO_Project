import java.text.SimpleDateFormat;
import java.util.Date;

public class Producator
{
    private String nume;
    private String tara;
    private Boolean statusContract;
    private Date contractStart;
    private Date contractEnd;

    public void setNume(String Nume)
    {
        this.nume = Nume;
    }

    public void setTara(String Tara)
    {
        this.tara = Tara;
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

    public String getTara()
    {
        return this.tara;
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

    public void StatusContract()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        if(date.after(contractEnd))
        {
            this.statusContract = false;
        }
        else
        {
            if(date.before(contractStart))
            {
                this.statusContract = false;
            }
            else
            {
                if(date.after(contractStart) && date.before(contractEnd))
                {
                    this.statusContract = true;
                }
            }
        }
    }



}
