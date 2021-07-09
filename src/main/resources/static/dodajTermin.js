$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "TRENER") {
            }
            else {
                alert("Nemate pristup ovoj stranici!");
                window.location.href = "login.html";
            }
        },
        error: function (response) {
            alert("Nemate pristup ovoj stranici!");
            window.location.href = "login.html";
        }
    });

});
$(document).ready(function() {
    let id = window.localStorage.getItem("Id");
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/sale/saleTrener/"+id,
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let sala of response){
                let row = "<tr>";
                row+= "<td>" + sala.oznakaSale + "</td>";
                row+= "<td><input type='checkbox' data='"+sala.id+"'></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });
    $("#terminSubmit").on("click", function(event){
        event.preventDefault();

        console.log(2);
        
        let idTreninga = window.localStorage.getItem("noviTerminId");
        let brojPrijavljenihClanova = 0;
        let cena = $("#cena").val();
        let vreme = $("#vreme").val();
        
        let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
            }
        })
    
        let newTermin = {
            brojPrijavljenihClanova,
            cena,
            vreme,
            ids,
            idTreninga

        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/api/termini/dodaj/"+idTreninga+"/"+ids,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newTermin),
            success: function(response){
                console.log(response);
    
                alert("Termin" +response.id + "je dodat");
                window.location.href = "trenerTermini.html";
    
            },
            error:function(e){
                alert("Greska prilikom dodavanja");
            }
    
        });
    
    
    });
    
});
