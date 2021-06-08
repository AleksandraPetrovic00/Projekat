$("#signup_btn").on("click", function(event){
    event.preventDefault();

    let ime = $("#ime_reg").val();
    let prezime = $("#prezime_reg").val();
    let korisnickoIme = $("#korisnickoIme_reg").val();
    let lozinka = $("#lozinka_reg").val();
    let emailAdresa = $("#emailAdresa_reg").val();
    let kontaktTelefon = $("#kontaktTelefon_reg").val();
    let datumRodjenja = $("#datumRodjenja_reg").val();


    let newTrener = {
        korisnickoIme,
        lozinka,
        ime,
        prezime,
        kontaktTelefon,
        emailAdresa,
        datumRodjenja,
        uloga: "TRENER"
    }
    console.log(newKorisnik)
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/api/korisnici/registracija",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTrener),
        success: function(response){
            console.log(response);

            alert("Trener" +response.id + "je dodat");
            window.location.href = "login.html";

        },
        error:function(e){
            alert("Greska prilikom registracije");
        }


    });


});