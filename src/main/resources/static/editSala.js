$("#salaSubmit").on("click", function(event){
    event.preventDefault();

    let id=window.localStorage.getItem("salaId");
    let kapacitet = $("#kapacitet").val();
    let oznakaSale = $("#oznakaSale").val();

    let updateSala = {
        kapacitet,
        oznakaSale
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8090/api/sale/update/"+id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(updateSala),
        success: function(response){
            console.log(response);
            alert("Sala " +response.id + " je izmenjena");
        },
        error:function(e){
            alert("Greska prilikom izmene");
        }


    });


});