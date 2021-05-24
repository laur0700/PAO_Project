public class Produse
{
    private Integer id;
    private String nume;
    private String numeCategorie;
    private String numeProducator;
    private String numeDistribuitor;
    private Float pret;
    private Boolean statusStoc;
    private String descriere;
    private Integer id_magazin;
    private Integer stoc_magazin;
    private Integer id_magazin_online;
    private Integer stoc_magazin_online;
    private Integer id_depozit;
    private Integer stoc_depozit;

    public void setId(Integer Id)
    {
        this.id = Id;
    }

    public void setNume(String Nume)
    {
        this.nume = Nume;
    }

    public void setNumeCategorie(String Nume)
    {
        this.numeCategorie = Nume;
    }

    public void setNumeProducator(String Nume)
    {
        this.numeProducator = Nume;
    }

    public void setNumeDistribuitor(String Nume)
    {
        this.numeDistribuitor = Nume;
    }

    public void setPret(Float Pret)
    {
        this.pret = Pret;
    }

    public void setStatusStoc(Boolean StatusStoc)
    {
        this.statusStoc = StatusStoc;
    }

    public void setDescriere(String Descrirere)
    {
        this.descriere = Descrirere;
    }

    public void setId_magazin(Integer Id)
    {
        this.id_magazin = Id;
    }

    public void setStoc_magazin(Integer N)
    {
        this.stoc_magazin = N;
    }

    public void setId_magazin_online(Integer id_magazin_online)
    {
        this.id_magazin_online = id_magazin_online;
    }

    public void setStoc_magazin_online(Integer stoc_magazin_online)
    {
        this.stoc_magazin_online = stoc_magazin_online;
    }

    public void setId_depozit(Integer id_depozit)
    {
        this.id_depozit = id_depozit;
    }

    public void setStoc_depozit(Integer stoc_depozit)
    {
        this.stoc_depozit = stoc_depozit;
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getNume()
    {
        return this.nume;
    }

    public String getNumeCategorie(){ return this.numeCategorie; }

    public String getNumeProducator()
    {
        return this.numeProducator;
    }

    public String getNumeDistribuitor()
    {
        return this.numeDistribuitor;
    }

    public Float getPret()
    {
        return this.pret;
    }

    public Boolean getStatusStoc()
    {
        return this.statusStoc;
    }

    public String getDescriere()
    {
        return this.descriere;
    }

    public Integer getId_magazin()
    {
        return this.id_magazin;
    }

    public Integer getStoc_magazin()
    {
        return this.stoc_magazin;
    }

    public Integer getId_magazin_online()
    {
        return this.id_magazin_online;
    }

    public Integer getStoc_magazin_online()
    {
        return this.stoc_magazin_online;
    }

    public Integer getId_depozit()
    {
        return this.id_depozit;
    }

    public Integer getStoc_depozit()
    {
        return this.stoc_depozit;
    }


}
