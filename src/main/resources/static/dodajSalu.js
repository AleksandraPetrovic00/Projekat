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
                row+= "<td><input type='checkbox' data='"+fitnesCentar.id+"'></td>"
                row+="</tr>";

                $('.content-table').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }

    });
    $("#salaSubmit").on("click", function(event){
        event.preventDefault();

        console.log(2);
    
        let kapacitet = $("#kapacitet").val();
        let oznakaSale = $("#oznakaSale").val();
        
        let ids=[]
        $("[type='checkbox']").each(function(elem){
            console.log(this)
            if($(this).is(":checked")){
                ids.push($(this).attr("data"));
            }
        })
    
        let newSala = {
            kapacitet,
            oznakaSale,
            ids
        }
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/api/sale/dodaj/"+ids,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newSala),
            success: function(response){
                console.log(response);
    
                alert("Sala" +response.id + "je dodata");
              // window.location.href = "fitnesCentri.html";
    
            },
            error:function(e){
                alert("Greska prilikom dodavanja");
            }
    
        });
    
    
    });
    
});
