    $("#signup_btn").on("click", function(event){
        event.preventDefault();

        let ime = $("#ime_signup").val();
        let prezime = $("#prezime_signup").val();
        let korisnickoIme = $("#korisnickoIme_signup").val();
        let lozinka = $("#lozinka_signup").val();
        let emailAdresa = $("#emailAdresa_signup").val();
        let kontaktTelefon = $("#kontaktTelefon_signup").val();
        let datumRodjenja = $("#datumRodjenja_signup").val();


        let newKorisnik = {
            korisnickoIme,
            lozinka,
            ime,
            prezime,
            kontaktTelefon,
            emailAdresa,
            datumRodjenja,
            uloga: "CLAN"
        }
        console.log(newKorisnik)
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/api/korisnici/registracija",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newKorisnik),
            success: function(response){
                console.log(response);

                alert("Korisnik" +response.id + "je dodat");
                window.location.href = "login.html";

            },
            error:function(e){
                alert("Greska prilikom registracije");
            }


        });


    });