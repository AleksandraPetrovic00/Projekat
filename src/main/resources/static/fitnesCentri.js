$(document).ready(function (event) {

    let newUpit = JSON.parse(window.localStorage.getItem("korisnik"));
    $.ajax({
        type: "POST",
        url: "http://localhost:8090/pristup",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUpit),
        success: function (response) {
            if (response.uloga === "ADMINISTRATOR") {
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
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/fitnesCentri",
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);

            for(let fitnesCentar of response){
                let row = "<tr>";
                row+= "<td>" + fitnesCentar.id + "</td>";
                row+= "<td>" + fitnesCentar.naziv + "</td>";
                row+= "<td>" + fitnesCentar.adresa + "</td>";
                row+= "<td>" + fitnesCentar.brojTelefonaCentrale + "</td>";
                row+= "<td>" + fitnesCentar.email + "</td>";
                row+="<td><button class='izmeni' onClick=window.location.href='editFitnesCentar.html' data-id='"+fitnesCentar.id+"'>EDIT</button></td>";
                row+= "<td><input type='checkbox' data='"+fitnesCentar.id+"'></td>"
                row+="<td><button class='izaberi' onClick=window.location.href='sale.html' data-id='"+fitnesCentar.id+"'>SALE</button></td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });

    $(document).on('click', '.izmeni', function(){
        let fitnesCentarId = this.dataset.id;
        console.log(fitnesCentarId);
        localStorage.setItem("centarId", fitnesCentarId);
    })

    $(document).on('click', '.izaberi', function(){

        let fcid=this.dataset.id;
        console.log(fcid);
        localStorage.setItem("fitnesCentarSalaId", fcid);

    })


    $("#potvrdi").on("click", function(event){
        event.preventDefault();
        let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
                window.location.href="fitnesCentri.html"
            }
        })
        let duzina=ids.length;
        for(var i=0; i<duzina; i++)
        {
            $.ajax({
                type:"DELETE",
                url:"http://localhost:8090/api/fitnesCentri/"+ids[i],
                sataType:"json",
                contentType: "application/json",
                data: JSON.stringify(ids)
            
            });
        }
    })
    
});