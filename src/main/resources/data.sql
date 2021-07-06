INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('The Workout Zone', 'Zlatne Grede 4', '011200300', 'twz@gmail.com');
INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('Athlemotive', 'Strazilovska 12', '021255255', 'althlemotive@gmail.com');
INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('NS Gym', 'Bulevar Oslobodjenja 22', '021200300', 'ns.gym@gmail.com');
INSERT INTO FITNES_CENTAR (naziv, adresa, broj_telefona_centrale, email) VALUES ('Runnin', 'Cara Dusana 21', '021555444', 'runnin@gmail.com');


INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('aleksandrap', '000719', 'Aleksandra', 'Petrovic', '0658848889', 'aleksandrap1986@gmail.com', '19.07.2000.', 'ADMINISTRATOR', 'TRUE', null );
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('brkicee', 'elib2r1', 'Elena', 'Brkic', '0633672330', 'brkic.elena@gmail.com', '27.08.2000.', 'CLAN', 'TRUE', null );
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('nadjakanjuh', 'glasnogovornica','Nadja', 'Kanjuh', '0600797566','nadjakanjuh@gmail.com', '24.07.2000.','TRENER', 'TRUE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('markovasilic', 'marko','Marko', 'Vasilic', '0635697566','markovasilic@gmail.com', '15.09.1998.','TRENER', 'FALSE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('natkapatka', 'bijelopolje','Natasa', 'Djacic', '065214589','djacicnatasa@gmail.com', '06.02.2000.','CLAN', 'FALSE', null );
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('spankistepa', 'srb123','Stefan', 'Mladenovic', '066427302','stefanmladen@gmail.com', '03.11.2000.','TRENER', 'FALSE', 2);
INSERT INTO KORISNIK (korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, datum_rodjenja, uloga, aktivan, fitnescentar_korisnik_id) VALUES ('nikoladzoni', '1301n1k','Nikola', 'Petrovic', '065214546','nikolap@gmail.com', '13.01.2006.','TRENER', 'FALSE', 1);

INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Morning', 'Full body workout','CARDIO', '120', 3);
INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Gluteus&Core', 'Hard','CORE', '120', 3);
INSERT INTO TRENING (naziv, opis, tip_treninga, trajanje, korisnik_trening_id) VALUES ('Tone Up', 'Medium','HIIT', '120', 7);

INSERT INTO SALA (kapacitet, oznaka_sale, fitnescentarsala_id) VALUES ('20', 'A1', 2);
INSERT INTO SALA (kapacitet, oznaka_sale, fitnescentarsala_id) VALUES ('30', 'A2', 1);
INSERT INTO SALA (kapacitet, oznaka_sale, fitnescentarsala_id) VALUES ('25', 'A3', 2);

INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme, cena, sala_termin_id, treningtermin_id) VALUES ('10', '2021-06-01', '500', 1, 3);
INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme, cena, sala_termin_id, treningtermin_id) VALUES ('12', '2021-06-02', '450', 2, 2);
INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme, cena, sala_termin_id, treningtermin_id) VALUES ('8', '2021-06-01', '500', 2, 3);
INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme, cena, sala_termin_id, treningtermin_id) VALUES ('15', '2021-06-03', '500', 3, 1);
INSERT INTO TERMIN (broj_prijavljenih_clanova, vreme, cena, sala_termin_id, treningtermin_id) VALUES ('12', '2021-06-01', '450', 1, 2);

INSERT INTO OCENA (ocena, korisnik_ocena_id, termin_ocena_id) VALUES ('9', 2, 3);
INSERT INTO OCENA (ocena, korisnik_ocena_id, termin_ocena_id) VALUES ('10', 3, 2);








