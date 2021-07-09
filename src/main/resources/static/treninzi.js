let pretrazeniTreninzi;
let treninzi;
$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/termini/svitreninzi",
        dataType: "json",
        success: function(response) {
            console.log("USPESNO", response);
            console.log(response);
            treninzi = response;
            pretrazeniTreninzi = JSON.parse(JSON.stringify(treninzi));

            for(let trening of response){
                for(let trening1 of trening.listaTreninga){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table').append(row);
            }
        }
        },
        error: function(response) {
            console.log("ERROR: ", response);
        }
    });


    function pretrazi(){
        let naziv = $('#naziv').val();
        let opis = $('#opis').val();
        let cena = $('#cena').val();
        let vreme = $('#vreme').val();

        pretrazeniTreninzi = JSON.parse(JSON.stringify(trening));
        if(naziv!=""){
            pretrazeniTreninzi = pretrazeniTreninzi.filter(function (trening){
                return trening.naziv.includes(naziv);
            })
        }
        if(opis!=""){
            pretrazeniTreninzi = pretrazeniTreninzi.filter(function (trening){
                return trening.opis.includes(opis);
            })
        }
        if(cena!=""){
            for(let i in pretrazeniTreninzi){
                pretrazeniTreninzi[i].listaTreninga = pretrazeniTreninzi[i].listaTreninga.filter(function (trening1){
                    return trening.cena<=cena;
                })
            }
        }
        if(vreme!=""){
            for(let i in pretrazeniTreninzi){
                pretrazeniTreninzi[i].listaTreninga = pretrazeniTreninzi[i].listaTreninga.filter(function (trening1){
                    return (trening.vreme > vreme);
                })
            }
        }
    }

    /*$("#pretraga").on("click", function(event){
    event.preventDefault();

    let naziv = $('#naziv').val();
    let opis = $('#opis').val();
    let cena = $('#cena').val();
    let vreme = $('#vreme').val();

    if((naziv!="")&&(cena!="")){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/nazivcena?cena="+$('#cena').val()+"&naziv="+$('#naziv').val(),
            dataType: "json",

        success: function(response){
            console.log("SUCCESS".response);
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }*/

    /*if($('#opis').val()!=""){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/opis/"+$('#opis').val(),
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if($('#vreme').val()!=""){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/vreme/"+$('#vreme').val(),
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }


});


$('#tipovi').on('input', function(){
    let tipovi = $ (this).val();

    if(tipovi=="hiit"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/tip?tipTreninga=HIIT",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(tipovi=="core"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/tip?tipTreninga=CORE",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(tipovi=="yoga"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/tip?tipTreninga=YOGA",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(tipovi=="cardio"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/tip?tipTreninga=CARDIO",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

});

$('#sortiranje').on('input', function(){
    let sort = $ (this).val();

    if(sort=="cenar"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/cenarastuce",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(sort=="cenao"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/cenaopadajuce",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(sort=="vremer"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/vremerastuce",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(sort=="vremeo"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/vremeopadajuce",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

    if(sort=="sort"){
        $.ajax({
            type:"GET",
            url:"http://localhost:8090/api/termini/sortiranje",
            dataType: "json",

        success: function(response){
            console.log(response);

            $('.content-table tbody').html("");

            for(let trening of response){
                let row = "<tr>";
                row+= "<td>" + trening.id + "</td>";
                row+= "<td>" + trening.naziv + "</td>";
                row+= "<td>" + trening.tipTreninga + "</td>";
                row+= "<td>" + trening.opis + "</td>";
                row+= "<td>" + trening.vreme + "</td>";
                row+= "<td>" + trening.oznakaSale + "</td>";
                row+= "<td>" + trening.trajanje + "</td>";
                row+= "<td>" + trening.cena + "</td>";
                row+= "<td>" + trening.brojPrijavljenihClanova + "</td>";
                row+="</tr>";

                $('.content-table tbody').append(row);
            }

        },

            error: function(response){
                console.log("ERROR", response);
            }
        });

    }

});



});