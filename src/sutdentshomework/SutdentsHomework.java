package sutdentshomework;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SutdentsHomework {

    public static void main(String[] args) {

        int opcija;
        String potvrda;

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
                .buildSessionFactory();

        System.out.println("***BAZA PODATAKA STUDENATA***");

        do {
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);

            factory.openSession();
            Session session = factory.getCurrentSession();

            System.out.println("Izaberite opciju:\n\t1.Dodavanje Studenta\n\t2.Pretraga Studenta\n\t3.Izmena Studenta\n\t4.Brisanje Studenta"
                    + "\n\t5.Lista Svih Studenta\n\t6.Exit"
            );
            opcija = sc.nextInt();

            while (opcija < 1 || opcija > 6) {

                System.out.println("Pogrsan unos, unesite ponovo:");
                opcija = sc.nextInt();
            }

            OUTER:
            switch (opcija) {
                case 1:
                    session.beginTransaction();

                    Student s1 = new Student();
                    System.out.println("Dodavanje novog studenta, molimo Vas unesite podatke:");

                    System.out.println("Ime studenta:");
                    s1.setName(sc1.next());
                    System.out.println("Prezime studenta:");
                    s1.setSurname(sc1.next());
                    System.out.println("Jmbg studenta:");
                    s1.setJmbg(sc1.next());
                    System.out.println("Grad studenta:");
                    s1.setCity(sc1.next());

                    session.save(s1);

                    System.out.println("Student + " + s1.toString() + " spreman za unos u bazu. Potvrdite sa OK\nZa otkazivanje unesite 'cancel'");
                    potvrda = sc1.next();
                    if (potvrda.equalsIgnoreCase("cancel")) {
                        break;
                    } else {
                        session.getTransaction().commit();
                        System.out.println("Student " + s1.getName() + " " + s1.getSurname() + " " + "dodat u bazu.");

                        break;
                    }
                case 2:
                    System.out.println("Pretraga studenta po: \n\t1.Imenu?\n\t2.Prezimenu?\n\t3.Gradu?");
                    opcija = sc.nextInt();
                    while (opcija < 0 || opcija > 3) {
                        System.out.println("Pogresan unos, unesite ponovo:");
                        opcija = sc.nextInt();
                    }
                    switch (opcija) {
                        case 1: {
                            session.beginTransaction();
                            System.out.println("Unesite ime:");
                            String unos = sc.next();
                            List<Student> students = session.createQuery("from Student s where s.name = '" + unos + "'").getResultList();
                            System.out.println(students);
                            session.getTransaction().commit();
                            break OUTER;
                        }
                        case 2: {
                            session.beginTransaction();
                            System.out.println("Unesite prezime:");
                            String unos = sc.next();
                            List<Student> students = session.createQuery("from Student s where s.surname = '" + unos + "'").getResultList();
                            System.out.println(students);
                            session.getTransaction().commit();
                            break OUTER;
                        }
                        case 3: {
                            session.beginTransaction();
                            System.out.println("Unesite grad:");
                            String unos = sc.next();
                            List<Student> students = session.createQuery("from Student s where s.city = '" + unos + "'").getResultList();
                            System.out.println(students);
                            session.getTransaction().commit();
                            break OUTER;
                        }
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Izmena studenta");
                    session.beginTransaction();
                    List<Student> students = session.createQuery("from Student s where s.id = id").getResultList();

                    System.out.println("Izaberite Studenta: ");
                    for (Student s : students) {
                        System.out.println(s.listaId());
                    }
                    System.out.println("Unesite ID studenta koji se menja: ");
                    opcija = sc.nextInt();
                    Student s2 = session.get(Student.class, opcija);

                    System.out.println("Izabrani student: " + s2.toString() + "." + "\nOpcije za izmenu: ");
                    System.out.println("\t1.Izmena imena?\n\t2.Izmena prezimena?\n\t3.Izmena jmbg-a?\n\t4.Izmena grada?");
                    opcija = sc.nextInt();
                    while (opcija < 0 || opcija > 4) {
                        System.out.println("Pogresan unos, unesite ponovo: ");
                        opcija = sc.nextInt();
                    }
                    switch (opcija) {

                        case 1:
                            System.out.println("Unesite novo ime: ");
                            String ime = sc1.next();
                            s2.setName(ime);
                            session.saveOrUpdate(s2);
                            break;
                        case 2:
                            System.out.println("Unesite novo prezime: ");
                            String prezime = sc1.next();
                            s2.setSurname(prezime);
                            session.saveOrUpdate(s2);
                            break;
                        case 3:
                            System.out.println("Unesite nov jmbg: ");
                            String jmbg = sc1.next();
                            s2.setJmbg(jmbg);
                            session.saveOrUpdate(s2);
                            break;
                        case 4:
                            System.out.println("Unesite novi grad: ");
                            String grad = sc1.next();
                            s2.setCity(grad);
                            session.saveOrUpdate(s2);
                            break;

                    }
                    System.out.println("Uspesna izmena studenta: " + s2.getName() + ".");
                    session.getTransaction().commit();
                    break;
                case 4:
                    System.out.println("Brisanje studenta");
                    session.beginTransaction();
                    List<Student> students1 = session.createQuery("from Student s where s.id = id").getResultList();

                    System.out.println("Izaberite Studenta: ");
                    for (Student s : students1) {
                        System.out.println(s.listaId());
                    }
                    System.out.println("Unesite ID studenta koji se brise: ");
                    potvrda = sc1.next();
                    session.createQuery("delete from Student s where s.id = ' " + potvrda + "' ").executeUpdate();

                    System.out.println("Student uspesno obrisan!");
                    session.getTransaction().commit();
                    break;
                case 5:
                    session.beginTransaction();
                    List<Student> studentList = session.createQuery("from Student s where s.id = id").getResultList();
                    for (Student s : studentList) {
                        System.out.println(s.toString());
                    }
                    session.getTransaction().commit();
                    break;
                case 6:
                    System.out.println("***DOVIDJENJA***");
                    return;
                default:
                    break;
            }

        } while (opcija != 6);

    }

}
