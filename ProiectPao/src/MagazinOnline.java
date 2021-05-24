public class MagazinOnline extends Magazin
{
    private String adresaWeb;

    public void setAdresaWeb(String AdresaWeb)
    {
        this.adresaWeb = AdresaWeb;
    }

    public String getAdresaWeb()
    {
        return adresaWeb;
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

