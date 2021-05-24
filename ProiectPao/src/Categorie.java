public class Categorie
{
    private Integer id;
    private String nume;
    private Produse[] p = new Produse[10000];
    private int pIndex = 0;

    public void setId(Integer Id){ this.id = Id; }

    public void setNume(String Nume)
    {
        this.nume = Nume;
    }

    public Integer getId(){ return this.id; }

    public String getNume()
    {
        return this.nume;
    }

    public void adaugaProdus(Produse P)
    {
        this.p[pIndex] = P;
        pIndex = pIndex + 1;
    }

    public Produse getProdus(String Nume)
    {
        int i = 0;

        for(i = 1; i<=p.length; i++)
        {
            if(p[i].getNume() == Nume)
            {
                return p[i];
            }
        }

        return null;
    }
}
