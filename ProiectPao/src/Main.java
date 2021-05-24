import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main
{
    private static List<Produse> readProduse(String table)
    {
        Audit audit = new Audit();
        Driver driver = new Driver();

        List<Produse> produse = new ArrayList<>();
        ResultSet resultSet = driver.ReadFromDB(table);

        try
        {
            while (resultSet.next())
            {
                Produse p = new Produse();

                p.setId(resultSet.getInt("id"));
                String content = "p.setId()";
                audit.WriteToDB(content);

                p.setNume(resultSet.getString("nume"));
                content = "p.setNume()";
                audit.WriteToDB(content);

                p.setNumeCategorie(resultSet.getString("nume_categorie"));
                content = "p.setNumeCategorie()";
                audit.WriteToDB(content);

                p.setNumeProducator(resultSet.getString("nume_producator"));
                content = "p.setNumeProducator()";
                audit.WriteToDB(content);

                p.setNumeDistribuitor(resultSet.getString("nume_distribuitor"));
                content = "p.setNumeDistribuitor()";
                audit.WriteToDB(content);

                p.setPret(resultSet.getFloat("pret"));
                content = "p.setPret()";
                audit.WriteToDB(content);

                p.setDescriere(resultSet.getString("descriere"));
                content = "p.setDescriere()";
                audit.WriteToDB(content);

                p.setId_magazin(resultSet.getInt("id_magazin"));
                content = "p.setId_magazin()";
                audit.WriteToDB(content);

                p.setStoc_magazin(resultSet.getInt("stoc_magazin"));
                content = "p.setStoc_magazin()";
                audit.WriteToDB(content);

                p.setId_magazin_online(resultSet.getInt("id_magazin_online"));
                content = "p.setId_magazin_online()";
                audit.WriteToDB(content);

                p.setStoc_magazin_online(resultSet.getInt("stoc_magazin_online"));
                content = "p.setStoc_magazin_online()";
                audit.WriteToDB(content);

                p.setId_depozit(resultSet.getInt("id_depozit"));
                content = "p.setId_depozit()";
                audit.WriteToDB(content);

                p.setStoc_depozit(resultSet.getInt("stoc_depozit"));
                content = "p.setStoc_depozit()";
                audit.WriteToDB(content);

                if (p.getStoc_magazin() > 0 || p.getStoc_magazin_online() > 0 || p.getStoc_depozit() > 0)
                {
                    p.setStatusStoc(true);
                } else
                {
                    p.setStatusStoc(false);
                }

                produse.add(p);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return produse;
    }

    private static List<Categorie> readCategorii(String table)
    {
        Audit audit = new Audit();
        Driver driver = new Driver();

        List<Categorie> categorii = new ArrayList<>();
        ResultSet resultSet = driver.ReadFromDB(table);
        List<Produse> prod = readProduse("produse");

        try
        {
            while (resultSet.next())
            {
                Categorie c = new Categorie();

                c.setId(resultSet.getInt("id"));
                String content = "c.setId()";
                audit.WriteToDB(content);

                c.setNume(resultSet.getString("nume"));
                content = "c.setNume()";
                audit.WriteToDB(content);

                for (Produse x : prod)
                {
                    if (x.getNumeCategorie() == c.getNume())
                    {
                        c.adaugaProdus(x);
                    }
                }

                categorii.add(c);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return categorii;
    }

    private static List<Magazin> readMagazine(String table)
    {
        Audit audit = new Audit();
        Driver driver = new Driver();

        List<Magazin> magazine = new ArrayList<>();
        ResultSet resultSet = driver.ReadFromDB(table);
        List<Produse> prod = readProduse("produse");

        try
        {
            while (resultSet.next())
            {
                Magazin m = new Magazin();

                m.setId(resultSet.getInt("id"));
                String content = "m.setId()";
                audit.WriteToDB(content);

                m.setAdresa(resultSet.getString("adresa"));
                content = "m.setAdresa()";
                audit.WriteToDB(content);

                m.setNrTelefon(resultSet.getString("telefon"));
                content = "m.setNrTelefon()";
                audit.WriteToDB(content);

                for (Produse x : prod)
                {
                    if (x.getId_magazin() == m.getId() && x.getStatusStoc() == true)
                    {
                        m.adaugaProdus(x);
                    }
                }

                magazine.add(m);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return magazine;
    }

    private static List<MagazinOnline> readMagazineOnline(String table)
    {
        Audit audit = new Audit();
        Driver driver = new Driver();

        List<MagazinOnline> magazineOnline = new ArrayList<>();
        ResultSet resultSet = driver.ReadFromDB(table);
        List<Produse> prod = readProduse("produse");

        try
        {
            while (resultSet.next())
            {
                MagazinOnline m = new MagazinOnline();

                m.setId(resultSet.getInt("id"));
                String content = "m.setId()";
                audit.WriteToDB(content);

                m.setAdresaWeb(resultSet.getString("adresa_web"));
                content = "m.setAdresaWeb()";
                audit.WriteToDB(content);

                m.setNrTelefon(resultSet.getString("telefon"));
                content = "m.setNrTelefon()";
                audit.WriteToDB(content);

                for (Produse x : prod)
                {
                    if (x.getId_magazin_online() == m.getId() && x.getStatusStoc() == true)
                    {
                        m.adaugaProdus(x);
                    }
                }

                magazineOnline.add(m);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return magazineOnline;
    }

    private static List<Depozit> readDepozite(String table)
    {
        Audit audit = new Audit();
        Driver driver = new Driver();

        List<Depozit> depozite = new ArrayList<>();
        ResultSet resultSet = driver.ReadFromDB(table);
        List<Produse> prod = readProduse("produse");

        try
        {
            while (resultSet.next())
            {
                Depozit d = new Depozit();

                d.setId(resultSet.getInt("id"));
                String content = "d.setId()";
                audit.WriteToDB(content);

                d.setAdresa(resultSet.getString("adresa"));
                content = "d.setAdresa()";
                audit.WriteToDB(content);

                d.setNrTelefon(resultSet.getString("telefon"));
                content = "d.setNrTelefon()";
                audit.WriteToDB(content);

                for (Produse x : prod)
                {
                    if (x.getId_depozit() == d.getId() && x.getStatusStoc() == true)
                    {
                        d.adaugaProdus(x);
                    }
                }

                depozite.add(d);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return depozite;
    }

    public static void main(String[] args) throws IOException, CsvValidationException
    {
        Audit audit = new Audit();
        Driver driver = new Driver();
        String content = null;

        Scanner cin = new Scanner(System.in);
        Integer id = 10000;
        Boolean run = true;
        String command;

        List<Categorie> categorii = readCategorii("categorii");

        List<Magazin> magazine = readMagazine("magazine");

        List<MagazinOnline> magazineOnline = readMagazineOnline("magazine_online");

        List<Depozit> depozite = readDepozite("depozite");

        List<Produse> produse = readProduse("produse");

        Producator producator = new Producator();
        producator.setNume("Samsung");
        content = "producator.setNume()";
        audit.WriteToDB(content);

        producator.setTara("Coreea de Sud");
        content = "producator.setTara()";
        audit.WriteToDB(content);

        Distribuitor distribuitor = new Distribuitor();
        distribuitor.setNume("Distribuitor");
        content = "distribuitor.setNume()";
        audit.WriteToDB(content);

        while (run == true)
        {
            System.out.println("START MENU\n\n");
            System.out.println("-> magazine");
            System.out.println("-> depozite");
            System.out.println("-> produse\n\n");
            command = cin.nextLine();

            if (command.equals("magazine"))
            {
                System.out.println("ALEGE TIPUL MAGAZINULUI\n\n");
                System.out.println("-> fizice");
                System.out.println("-> online\n\n");
                command = cin.nextLine();

                if (command.equals("fizice"))
                {
                    System.out.println("MAGAZINE FIZICE\n\n");
                    for (Integer i = 0; i < magazine.size(); i++)
                    {
                        if (magazine.get(i).getAdresa() != null)
                        {
                            content = "magazine[i].getAdresa()";
                            audit.WriteToDB(content);

                            System.out.println(magazine.get(i).getId() + ": " + magazine.get(i).getAdresa());
                            content = "magazine[i].getAdresa()";
                            audit.WriteToDB(content);
                        }
                    }
                    System.out.println("\n\n");
                    System.out.println("SELECTATI MAGAZINUL PENTRU DETALII\n\n");

                    command = cin.nextLine();
                    if (command.equals("!back") || command.equals("exit"))
                    {
                        if (command.equals("!exit"))
                        {
                            run = false;
                        }
                    } else
                    {
                        Integer n = Integer.parseInt((command)) - 1;
                        System.out.println("\n\n");

                        while (command.equals("!back") != true)
                        {
                            System.out.println("Adresa: " + magazine.get(n).getAdresa() + "\n" + "Telefon: " + magazine.get(n).getNrTelefon() + "\n");
                            content = "magazine[n].getAdresa()";
                            audit.WriteToDB(content);
                            content = "magazine[n].getNrTelefon()";
                            audit.WriteToDB(content);

                            if (magazine.get(n).getStocStatus() == true)
                            {
                                for (int i = 1; i <= magazine.get(n).getMaximumId(); i++)
                                {
                                    if (magazine.get(n).getProdus(i) != null && magazine.get(n).getProdus(i).getStoc_magazin() > 0)
                                    {
                                        content = "magazine[n].getProdus()";
                                        audit.WriteToDB(content);

                                        System.out.println("Id: " + magazine.get(n).getProdus(i).getId());
                                        content = "magazine[n].getProdus(i).getId()";
                                        audit.WriteToDB(content);

                                        System.out.println("Nume: " + magazine.get(n).getProdus(i).getNume());
                                        content = "magazine[n].getProdus(i).getNume()";
                                        audit.WriteToDB(content);

                                        System.out.println("Pret: " + magazine.get(n).getProdus(i).getPret() + "\n\n");
                                        content = "magazine[n].getProdus(i).getPret()";
                                        audit.WriteToDB(content);
                                    }
                                }
                            } else
                            {
                                System.out.println("Stoc epuizat!\n\n");
                            }


                            command = cin.nextLine();

                            if (command.equals("!exit"))
                            {
                                run = false;
                            } else
                            {
                                if (command.equals("adauga"))
                                {
                                    while (command.equals("adauga"))
                                    {
                                        Produse P = new Produse();

                                        System.out.println("Nume: ");
                                        command = cin.nextLine();
                                        P.setNume(command);
                                        content = "P.setNume()";
                                        audit.WriteToDB(content);

                                        System.out.println("Categorie: ");
                                        command = cin.nextLine();
                                        P.setNumeCategorie(command);
                                        content = "P.setNumeCategorie()";
                                        audit.WriteToDB(content);

                                        System.out.println("Producator: ");
                                        command = cin.nextLine();
                                        P.setNumeProducator(command);
                                        content = "P.setNumeProducator()";
                                        audit.WriteToDB(content);

                                        System.out.println("Distribuitor: ");
                                        command = cin.nextLine();
                                        P.setNumeDistribuitor(command);
                                        content = "P.setNumeDistribuitor()";
                                        audit.WriteToDB(content);

                                        System.out.println("Pret: ");
                                        command = cin.nextLine();
                                        Float pret = Float.parseFloat(command);
                                        P.setPret(pret);
                                        content = "P.setPret()";
                                        audit.WriteToDB(content);

                                        System.out.println("Descriere: ");
                                        command = cin.nextLine();
                                        P.setDescriere(command);
                                        content = "P.setDescriere()";
                                        audit.WriteToDB(content);

                                        P.setId_magazin(magazine.get(n).getId());

                                        System.out.println("Numar Stoc: ");
                                        command = cin.nextLine();
                                        Integer Stoc = Integer.parseInt(command);
                                        P.setStoc_magazin(Stoc);
                                        content = "P.setStoc_magazin()";
                                        audit.WriteToDB(content);

                                        P.setId_magazin_online(1);
                                        P.setStoc_magazin_online(0);
                                        P.setId_depozit(1);
                                        P.setStoc_depozit(0);

                                        driver.AddProdus(P);
                                        magazine = readMagazine("magazine");
                                        content = "readMagazine()";
                                        audit.WriteToDB(content);
                                    }
                                } else
                                {
                                    System.out.println("\n\n");
                                    Integer x = Integer.parseInt((command));

                                    System.out.println("Id: " + magazine.get(n).getProdus(x).getId());
                                    content = "magazine[n].getProdus(i).getId()";
                                    audit.WriteToDB(content);

                                    System.out.println("Nume: " + magazine.get(n).getProdus(x).getNume());
                                    content = "magazine[n].getProdus(i).getNume()";
                                    audit.WriteToDB(content);

                                    System.out.println("Pret: " + magazine.get(n).getProdus(x).getPret());
                                    content = "magazine[n].getProdus(i).getPret()";
                                    audit.WriteToDB(content);

                                    System.out.println("Categorie: " + magazine.get(n).getProdus(x).getNumeCategorie());
                                    content = "magazine[n].getProdus(i).getNumeCategorie()";
                                    audit.WriteToDB(content);

                                    System.out.println("Producator: " + magazine.get(n).getProdus(x).getNumeProducator());
                                    content = "magazine[n].getProdus(i).getNumeProducator()";
                                    audit.WriteToDB(content);

                                    System.out.println("Distribuitor: " + magazine.get(n).getProdus(x).getNumeDistribuitor());
                                    content = "magazine[n].getProdus(i).getNumeDistribuitor()";
                                    audit.WriteToDB(content);

                                    System.out.println("Stoc Magazin: " + magazine.get(n).getProdus(x).getStoc_magazin());
                                    content = "magazine[n].getProdus(i).getStoc_magazin()";
                                    audit.WriteToDB(content);

                                    System.out.println("Descriere: " + magazine.get(n).getProdus(x).getDescriere() + "\n\n");
                                    content = "magazine[n].getProdus(i).getDescriere()";
                                    audit.WriteToDB(content);

                                    break;

                                }
                            }
                        }
                    }
                }

                if (command.equals("online"))
                {
                    System.out.println("MAGAZINE ONLINE\n\n");
                    for (Integer i = 0; i < magazineOnline.size(); i++)
                    {
                        if (magazineOnline.get(i).getAdresaWeb() != null)
                        {
                            System.out.println(magazineOnline.get(i).getId() + ": " + magazineOnline.get(i).getAdresaWeb());
                        }
                    }
                    System.out.println("\n\n");
                    System.out.println("SELECTATI MAGAZINUL PENTRU DETALII\n\n");

                    command = cin.nextLine();
                    if (command.equals("!back") || command.equals("!exit"))
                    {
                        if (command == "!exit")
                        {
                            run = false;
                        }
                    } else
                    {
                        Integer n = Integer.parseInt((command)) - 1;
                        System.out.println("\n\n");

                        while (command.equals("!back") != true)
                        {
                            System.out.println("URL: " + magazineOnline.get(n).getAdresaWeb());
                            System.out.println("Telefon: " + magazineOnline.get(n).getNrTelefon() + "\n");

                            if (magazineOnline.get(n).getStocStatus() == true)
                            {
                                for (int i = 1; i <= magazineOnline.get(n).getMaximumId(); i++)
                                {
                                    if (magazineOnline.get(n).getProdus(i) != null && magazineOnline.get(n).getProdus(i).getStoc_magazin_online() > 0)
                                    {
                                        System.out.println("Id: " + magazineOnline.get(n).getProdus(i).getId());
                                        System.out.println("Nume: " + magazineOnline.get(n).getProdus(i).getNume());
                                        System.out.println("Pret: " + magazineOnline.get(n).getProdus(i).getPret() + "\n\n");
                                    }
                                }
                            } else
                            {
                                System.out.println("Stoc epuizat!\n\n");
                            }


                            command = cin.nextLine();

                            if (command.equals("!exit"))
                            {
                                run = false;
                            } else
                            {
                                if (command.equals("adauga"))
                                {
                                    while (command.equals("adauga"))
                                    {
                                        Produse P = new Produse();

                                        System.out.println("Nume: ");
                                        command = cin.nextLine();
                                        P.setNume(command);
                                        content = "P.setNume()";
                                        audit.WriteToDB(content);

                                        System.out.println("Categorie: ");
                                        command = cin.nextLine();
                                        P.setNumeCategorie(command);
                                        content = "P.setNumeCategorie()";
                                        audit.WriteToDB(content);

                                        System.out.println("Producator: ");
                                        command = cin.nextLine();
                                        P.setNumeProducator(command);
                                        content = "P.setNumeProducator()";
                                        audit.WriteToDB(content);

                                        System.out.println("Distribuitor: ");
                                        command = cin.nextLine();
                                        P.setNumeDistribuitor(command);
                                        content = "P.setNumeDistribuitor()";
                                        audit.WriteToDB(content);

                                        System.out.println("Pret: ");
                                        command = cin.nextLine();
                                        Float pret = Float.parseFloat(command);
                                        P.setPret(pret);
                                        content = "P.setPret()";
                                        audit.WriteToDB(content);

                                        System.out.println("Descriere: ");
                                        command = cin.nextLine();
                                        P.setDescriere(command);
                                        content = "P.setDescriere()";
                                        audit.WriteToDB(content);

                                        P.setId_magazin_online(magazineOnline.get(n).getId());

                                        System.out.println("Numar Stoc: ");
                                        command = cin.nextLine();
                                        Integer Stoc = Integer.parseInt(command);
                                        P.setStoc_magazin_online(Stoc);
                                        content = "P.setStoc_magazin_online()";
                                        audit.WriteToDB(content);

                                        P.setId_magazin(1);
                                        P.setStoc_magazin(0);
                                        P.setId_depozit(1);
                                        P.setStoc_depozit(0);

                                        driver.AddProdus(P);
                                        magazineOnline = readMagazineOnline("magazine_online");
                                        content = "readMagazineOnline()";
                                        audit.WriteToDB(content);
                                    }
                                } else
                                {
                                    System.out.println("\n\n");
                                    Integer x = Integer.parseInt((command));

                                    System.out.println("Id: " + magazineOnline.get(n).getProdus(x).getId());
                                    content = "magazineOnline[n].getProdus(i).getId()";
                                    audit.WriteToDB(content);

                                    System.out.println("Nume: " + magazineOnline.get(n).getProdus(x).getNume());
                                    content = "magazineOnline[n].getProdus(i).getNume()";
                                    audit.WriteToDB(content);

                                    System.out.println("Pret: " + magazineOnline.get(n).getProdus(x).getPret());
                                    content = "magazineOnline[n].getProdus(i).getPret()";
                                    audit.WriteToDB(content);

                                    System.out.println("Categorie: " + magazineOnline.get(n).getProdus(x).getNumeCategorie());
                                    content = "magazineOnline[n].getProdus(i).getNumeCategorie()";
                                    audit.WriteToDB(content);

                                    System.out.println("Producator: " + magazineOnline.get(n).getProdus(x).getNumeProducator());
                                    content = "magazineOnline[n].getProdus(i).getNumeProducator()";
                                    audit.WriteToDB(content);

                                    System.out.println("Distribuitor: " + magazineOnline.get(n).getProdus(x).getNumeDistribuitor());
                                    content = "magazineOnline[n].getProdus(i).getNumeDistribuitor()";
                                    audit.WriteToDB(content);

                                    System.out.println("Stoc Magazin: " + magazineOnline.get(n).getProdus(x).getStoc_magazin_online());
                                    content = "magazineOnline[n].getProdus(i).getStoc_magazin_online()";
                                    audit.WriteToDB(content);

                                    System.out.println("Descriere: " + magazineOnline.get(n).getProdus(x).getDescriere() + "\n\n");
                                    content = "magazineOnline[n].getProdus(i).getDescriere()";
                                    audit.WriteToDB(content);

                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (command.equals("depozite"))
            {
                System.out.println("DEPOZITE\n\n");
                for (Integer i = 0; i < depozite.size(); i++)
                {
                    if (depozite.get(i).getAdresa() != null)
                    {
                        System.out.println(depozite.get(i).getId() + ": " + depozite.get(i).getAdresa());
                    }
                }
                System.out.println("\n\n");
                System.out.println("SELECTATI DEPOZITUL PENTRU DETALII\n\n");

                command = cin.nextLine();
                if (command.equals("!back") || command.equals("!exit"))
                {
                    if (command.equals("!exit"))
                    {
                        run = false;
                    }
                } else
                {
                    Integer n = Integer.parseInt((command)) - 1;
                    System.out.println("\n\n");

                    while (command.equals("!back") != true)
                    {
                        System.out.println("Adresa: " + depozite.get(n).getAdresa());
                        System.out.println("Telefon: " + depozite.get(n).getNrTelefon() + "\n");

                        if (depozite.get(n).getStocStatus() == true)
                        {
                            for (int i = 1; i <= depozite.get(n).getMaximumId(); i++)
                            {
                                if (depozite.get(n).getProdus(i) != null && depozite.get(n).getProdus(i).getStoc_depozit() > 0)
                                {
                                    System.out.println("Id: " + depozite.get(n).getProdus(i).getId());
                                    System.out.println("Nume: " + depozite.get(n).getProdus(i).getNume());
                                    System.out.println("Pret: " + depozite.get(n).getProdus(i).getPret() + "\n\n");
                                }
                            }
                        } else
                        {
                            System.out.println("Stoc epuizat!\n\n");
                        }


                        command = cin.nextLine();

                        if (command.equals("!exit"))
                        {
                            run = false;
                        }
                        else
                        {
                            if (command.equals("adauga"))
                            {
                                while (command.equals("adauga"))
                                {
                                    Produse P = new Produse();

                                    System.out.println("Nume: ");
                                    command = cin.nextLine();
                                    P.setNume(command);
                                    content = "P.setNume()";
                                    audit.WriteToDB(content);

                                    System.out.println("Categorie: ");
                                    command = cin.nextLine();
                                    P.setNumeCategorie(command);
                                    content = "P.setNumeCategorie()";
                                    audit.WriteToDB(content);

                                    System.out.println("Producator: ");
                                    command = cin.nextLine();
                                    P.setNumeProducator(command);
                                    content = "P.setNumeProducator()";
                                    audit.WriteToDB(content);

                                    System.out.println("Distribuitor: ");
                                    command = cin.nextLine();
                                    P.setNumeDistribuitor(command);
                                    content = "P.setNumeDistribuitor()";
                                    audit.WriteToDB(content);

                                    System.out.println("Pret: ");
                                    command = cin.nextLine();
                                    Float pret = Float.parseFloat(command);
                                    P.setPret(pret);
                                    content = "P.setPret()";
                                    audit.WriteToDB(content);

                                    System.out.println("Descriere: ");
                                    command = cin.nextLine();
                                    P.setDescriere(command);
                                    content = "P.setDescriere()";
                                    audit.WriteToDB(content);

                                    P.setId_depozit(depozite.get(n).getId());

                                    System.out.println("Numar Stoc: ");
                                    command = cin.nextLine();
                                    Integer Stoc = Integer.parseInt(command);
                                    P.setStoc_depozit(Stoc);
                                    content = "P.setStoc_depozite()";
                                    audit.WriteToDB(content);

                                    P.setId_magazin(1);
                                    P.setStoc_magazin(0);
                                    P.setId_magazin_online(1);
                                    P.setStoc_magazin_online(0);

                                    driver.AddProdus(P);
                                    depozite = readDepozite("depozite");
                                    content = "readDepozite()";
                                    audit.WriteToDB(content);
                                }
                            }
                            else
                            {

                                System.out.println("\n\n");
                                Integer x = Integer.parseInt((command));

                                System.out.println("Id: " + depozite.get(n).getProdus(x).getId());
                                content = "depozite[n].getProdus(i).getId()";
                                audit.WriteToDB(content);

                                System.out.println("Nume: " + depozite.get(n).getProdus(x).getNume());
                                content = "depozite[n].getProdus(i).getNume()";
                                audit.WriteToDB(content);

                                System.out.println("Pret: " + depozite.get(n).getProdus(x).getPret());
                                content = "depozite[n].getProdus(i).getPret()";
                                audit.WriteToDB(content);

                                System.out.println("Categorie: " + depozite.get(n).getProdus(x).getNumeCategorie());
                                content = "depozite[n].getProdus(i).getNumeCategorie()";
                                audit.WriteToDB(content);

                                System.out.println("Producator: " + depozite.get(n).getProdus(x).getNumeProducator());
                                content = "depozite[n].getProdus(i).getNumeProducator()";
                                audit.WriteToDB(content);

                                System.out.println("Distribuitor: " + depozite.get(n).getProdus(x).getNumeDistribuitor());
                                content = "depozite[n].getProdus(i).getNumeDistribuitor()";
                                audit.WriteToDB(content);

                                System.out.println("Stoc Magazin: " + depozite.get(n).getProdus(x).getStoc_depozit());
                                content = "depozite[n].getProdus(i).getStoc_depozit()";
                                audit.WriteToDB(content);

                                System.out.println("Descriere: " + depozite.get(n).getProdus(x).getDescriere() + "\n\n");
                                content = "depozite[n].getProdus(i).getDescriere()";
                                audit.WriteToDB(content);

                                break;
                            }
                        }
                    }
                }
            }

            if(command.equals("produse"))
            {
                System.out.println("Produse\n\n");
                for (Integer i = 0; i < produse.size(); i++)
                {
                    System.out.println("Id: " + produse.get(i).getId());
                    System.out.println("Nume: " + produse.get(i).getNume());
                    System.out.println("Pret: " + produse.get(i).getPret() + "\n\n");
                }

                command = cin.nextLine();

                if(command.equals("!back") != true || command.equals("!exit") != true)
                {
                    System.out.println("\n\n");
                    Integer x = Integer.parseInt((command))-1;

                    System.out.println("Id: " + produse.get(x).getId());
                    content = "produse.get(x).getId()";
                    audit.WriteToDB(content);

                    System.out.println("Nume: " + produse.get(x).getNume());
                    content = "produse.get(x).getNume()";
                    audit.WriteToDB(content);

                    System.out.println("Pret: " + produse.get(x).getPret());
                    content = "produse.get(x).getPret()";
                    audit.WriteToDB(content);

                    System.out.println("Categorie: " + produse.get(x).getNumeCategorie());
                    content = "produse.get(x).getNumeCategorie()";
                    audit.WriteToDB(content);

                    System.out.println("Producator: " + produse.get(x).getNumeProducator());
                    content = "produse.get(x).getNumeProducator()";
                    audit.WriteToDB(content);

                    System.out.println("Distribuitor: " + produse.get(x).getNumeDistribuitor());
                    content = "produse.get(x).getNumeDistribuitor()";
                    audit.WriteToDB(content);

                    System.out.println("Stoc Magazin: " + produse.get(x).getStoc_depozit());
                    content = "produse.get(x).getStoc_depozit()";
                    audit.WriteToDB(content);

                    System.out.println("Descriere: " + produse.get(x).getDescriere() + "\n\n");
                    content = "produse.get(x).getDescriere()";
                    audit.WriteToDB(content);
                }
            }

            if (command.equals("!exit"))
            {
                run = false;
            }

        }


    }
}
