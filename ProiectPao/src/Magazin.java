import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Magazin
{
    private Integer id;
    private String adresa;
    private String nrTelefon;
    protected List<Produse> stoc = new ArrayList<>();

    public void setId(Integer Id){ this.id = Id; }

    public void setAdresa(String Adresa)
    {
        this.adresa = Adresa;
    }

    public void setNrTelefon(String NrTelefon)
    {
        this.nrTelefon = NrTelefon;
    }

    public void adaugaProdus(Produse P)
    {
        stoc.add(P);
    }

    public Integer getId(){ return this.id; }

    public String getAdresa()
    {
        return this.adresa;
    }

    public String getNrTelefon()
    {
        return this.nrTelefon;
    }

    public Integer getStocNumber(){ return this.stoc.size(); }

    public Boolean getStocStatus()
    {
        if(stoc.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Integer getMaximumId()
    {
        return stoc.get(stoc.size()-1).getId();
    }

    public Produse getProdus(Integer Id)
    {
        for(Produse p : stoc)
        {
            if(p.getId() == Id)
            {
                return p;
            }
        }

        return null;
    }
}
