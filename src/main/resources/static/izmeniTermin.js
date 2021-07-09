$(document).ready(function(){
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
})


$("#terminSubmit").on("click", function(event){
    event.preventDefault();

    let idTermina=window.localStorage.getItem("izmeniTerminId");
    let cena = $("#cena").val();
    let vreme = $("#vreme").val();
    let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
            }
        })

    let updateTermin = {
        cena,
        vreme,
        ids
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/termini/update/"+idTermina+"/"+ids,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateTermin),
        success: function(response){
            console.log(response);
            alert("Termin " +response.id + " je izmenjen");
            window.location.href="trenerTermini.html";
        },
        error:function(e){
            alert("Ne mozete izmeniti termin koji ima prijavljene clanove");
        }


    });


});