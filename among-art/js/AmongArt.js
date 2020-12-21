$(document).ready(function () { 

    //global variables
    var idArticle = 1;
	var depictChosen;
	
	updateGallery();

    // Choose a container element for the timeline
    var timelineContainer = $("#timeline-container");
    var timelineContainerWidth = timelineContainer.css("width");

    // Initialise the timeline with default options (see online documentation for all available options)
    var timeline = new Histropedia.Timeline(timelineContainer[0],
    {"width": timelineContainerWidth,
	
    onArticleClick: function(article) { 

        $("#dropdown-menu").text("Choose a depiction");
        $("#dropdown-elements").empty();
        
	  	$.ajax({
            type: "GET",
            url: "http://localhost:8080/user/depicts-work?idWikidataWork=" + article.data.idItem,
            
			success: function(resp){
                
                resp.items.forEach(function(inception){
                    depictOption = $('<a class="dropdown-item btn btn-link depict-button"></a>');
                    depictOption.text(inception.nameDepict);
                    $("#dropdown-elements").append(depictOption);
                    
                    depictOption.click(function () {
                        $("#dropdown-menu").text(inception.nameDepict);
                        depictChosen = inception.idWikidataDepict;
                    });
				
		        });
		    }
        });
    },
    onArticleDoubleClick: function(article) { 
	

        $("#description-container").remove();
        $("#timeline-container").after('<div id="description-container" class="carousel slide" data-ride="carousel" style="margin-bottom:30px"></div>');
	  	$.ajax({
            type: "GET",
            url: "http://localhost:8080/user/get-data-work?idNameWorkWikidate=" + article.data.idItem,
            success: function(resp){
				
				//Description painting container
				$("#description-container").append('<button style="padding: 10px 24px;" type="button" id="close-description" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>')
				$("#description-container").append('<div id="carousel-inner" class="carousel-inner">')
				$("#carousel-inner").append('<div id="carousel-item-active-1" class="carousel-item active">')
                $("#carousel-item-active-1").append('<h1  id = "title-painting"></h1>'); 
                $("#carousel-item-active-1").append('<h2  id = "name-creator"></h2>');
                $("#carousel-item-active-1").append('<img id = "image-painting" style="float:left;margin:50px 50px 20px 15px" width="400" height="400" ></img>');
                $("#carousel-item-active-1").append('<h5 style="margin:50px 50px 50px 50px" id = "description-painting"></h5>');
			    $('#title-painting').text(resp.nameItem);
			    $('#name-creator').text(resp.nameCreator);
                $('#image-painting').attr("src", resp.imageItem);
                
				var description = "Description not found"

                if (resp.descriptionItem == null) {
                    $('#description-painting').text(description);
                } else {
                    $('#description-painting').text(resp.descriptionItem);
                }
				
				//Description author container
				$("#carousel-inner").append('<div id="carousel-item-active-2" class="carousel-item">')
				$("#carousel-item-active-2").append('<h1  id = "name-creator-2"></h1>');
				$("#carousel-item-active-2").append('<img id = "image-creator" style="float:left;margin:50px 50px 20px 15px" width="400" height="400" ></img>');
                $("#carousel-item-active-2").append('<h5 style="margin:50px 50px 50px 50px" id = "description-creator"></h5>');
			    $('#name-creator-2').text(resp.nameCreator);
				
				if (resp.descriptionCreator == null) {
                    $('#description-creator').text(description);
                } else {
                    $('#description-creator').text(resp.descriptionCreator);
                }
				$('#image-creator').attr("src", resp.imageCreator);
				
				
				$("#description-container").append('<a id="left_arrow" class="carousel-control-prev" href="#description-container" role="button" data-slide="prev"><span class="carousel-control-prev-icon" aria-hidden="true"></span></a>');
				$("#description-container").append('<a id="right_arrow"class="carousel-control-next" href="#description-container" role="button" data-slide="next"><span class="carousel-control-next-icon" aria-hidden="true"></span></a>');

                $("#close-description").click(function(){
                    $("#description-container").remove();
			    });
				
				$("#dropdown-elements").empty();
		    }
        });
    }
    })


    /* ---------- EVENT HANDLERS --------------*/
	
	$("#form-button").click(function () {
        var startYear = $('#start-year').val();
		var lastYear = $('#last-year').val();
		var limit = $('#limit').val();

		var data = {
			    "finishYear": lastYear,
				"idWikidataDepict": depictChosen,
				"limit": limit,
				"startingYear": startYear
	    }
		$.ajax({
		  type: "POST",
		  url: 'http://localhost:8080/user/similar-work',
		  data:  JSON.stringify(data),
		  headers: {'Content-Type': 'application/json'},
		  success: function(resp){		
		  
				resp.items.forEach(function(work){
                    
                createArticle(work.nameItem, work.nameCreator, work.dateItem, work.imageItem, work.idWikidataItem, work.idWikidataCreator );
                $("#description-container").remove();
                $("#dropdown-menu").text("Choose a depiction");
                $("#dropdown-elements").empty();
				
		        });
			}
		});

    });	

	$("#updateGallery").click(function(){
			
			$("#featured-paintings-container").empty();
			$("#spinner-container").append('<div class="spinner-grow text-danger" role="status"><span class="sr-only">Loading...</span></div>');
			$("#spinner-container").append('<div class="spinner-grow text-warning" role="status"><span class="sr-only">Loading...</span></div>');
			$("#spinner-container").append('<div class="spinner-grow text-info" role="status"><span class="sr-only">Loading...</span></div>');
			updateGallery();
	
	});
		


    $(".notable-works-btn").click(function () {
		var article = timeline.getActiveArticle();
		var idAuthor = article.data.idAuthor;

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/user/get-notable-work?idWikidataCreator=" + idAuthor,
            success: function(resp){

                resp.notableWorks.forEach(function(work){
                    createArticle(work.nameWork, resp.nameCreator, work.dateWork, work.imageWork, work.idWikidataWork, idAuthor );
                    $("#description-container").remove();
                    $("#dropdown-menu").text("Choose a depiction");
                    $("#dropdown-elements").empty();
                });
            }
        });

    });
	
    $("#reset-btn").click(function () {

        $("#description-container").remove();
        $("#dropdown-menu").text("Choose a depiction");
        $("#dropdown-elements").empty();
		
        var id;
		for (id = 1;id<=idArticle;id++){
			timeline.removeArticleById(id);
			timeline.redraw();
        }
        idArticle = 1;

    });	
	
	$(".delete-btn").click(function () {

        $("#description-container").remove();
        $("#dropdown-menu").text("Choose a depiction");
        $("#dropdown-elements").empty();
			
		var article = timeline.getActiveArticle();
		timeline.removeArticleById(article.id);	
		timeline.redraw();
    });


    $("#my-search-input").autocomplete({
        appendTo: ".search-bar",
        minLength: 2,
		select: function( event, ui ) {
			
			$.ajax({
            type: "GET",
            url: "http://localhost:8080/user/get-data-work?idNameWorkWikidate=" + ui.item.idWikidataItem,
            success: function(resp){
				
                createArticle(resp.nameItem, resp.nameCreator, resp.dateItem, resp.imageItem, ui.item.idWikidataItem , ui.item.idWikidataAuthor);
                $("#description-container").remove();
                $("#dropdown-menu").text("Choose a depiction");
                $("#dropdown-elements").empty();
            }
        });
		
		},
        source: function (request, response){		
            $.ajax({
                url: "http://localhost:8080/user/get-name-painting",
                data: {
                nameWork: request.term
                },
                success: function(resp) {

					
					var suggestions = [];
					resp.items.forEach(function(item){

					
						 var suggestion = {
							 
							 label: item.nameItem + " , " +item.nameCreator,
							 value: item.nameItem,
							 idWikidataItem: item.idWikidataItem,
							 idWikidataAuthor: item.idWikidataAuthor,
						 }
                         suggestions.push(suggestion);
                    });			

                    response($.map(resp, function(item) {
                        return suggestions
                    }));
                }
            })
        }
    });
	
	
	/* --- CUSTOM FUNCTIONS ---*/

    function updateGallery(){
		
		$.ajax({
		type: "GET",
		url: "http://localhost:8080/user/famous-works",
		success: function(resp){
				
            resp.items.forEach(function(famousWork){
                divFamousWorks = $('<div class="container"></div>');
                $("#featured-paintings-container").append(divFamousWorks)
                divFamousWorks.append('<img src="'+famousWork.imageItem+'" alt=""><span class="title">'+famousWork.nameItem+'</span><span class="text">'+famousWork.nameCreator+'</span>');
                
                divFamousWorks.click(function () {
                    createArticle(famousWork.nameItem, famousWork.nameCreator, famousWork.dateItem, famousWork.imageItem, famousWork.idWikidataItem , famousWork.idWikidataCreator);
                    $("#description-container").remove();
                    $("#dropdown-menu").text("Choose a depiction");
                    $("#dropdown-elements").empty();
                });
            });
			
			$("#spinner-container").empty();
        }

    });
    }

    function createArticle (nameItem, nameCreator, dateItem, imageItem, idWikidataItem, idWikidataAuthor) {

        var addArticle = true;

        for (i=1; i < idArticle; i++){
            if (timeline.getArticleById(i) !== undefined){
                if (timeline.getArticleById(i).data.idItem == idWikidataItem){
                    addArticle = false;
                }
            }
        }
        if (addArticle == true){
            var newArticle = {
                id: idArticle,
                title: nameItem,
                subtitle: nameCreator,
                idItem: idWikidataItem,
                idAuthor: idWikidataAuthor,
                from: {
                    year: dateItem
                },
                to: {
                    year: dateItem
                },
                imageUrl: imageItem
            }
            timeline.load([newArticle]);
            timeline.setStartDate(dateItem + "-01-01", 600);
            idArticle++;
        }
    }
});