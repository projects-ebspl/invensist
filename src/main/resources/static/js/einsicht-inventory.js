(function ( $ ) {
	$.fn.tag = function() {
		return this.prop("tagName");
	};
	
	$.fn.dualSelector = function() {
		var availableItmes = $("#available-itmes").inv_table();
		var selectedItmes = $("#selected-itmes").inv_table();
		$(this).find("#select").click(function(){
			$(selectedItmes).append(availableItmes.selectedNodes());
		});
		$(this).find("#remove").click(function(){
			$(availableItmes).append(selectedItmes.selectedNodes());
		});
	};
	
	
	$.fn.multiselect = function() {
	    $(this).each(function() {
	        var checkboxes = $(this).find("input:checkbox");
	        checkboxes.each(function() {
	            var checkbox = $(this);
	            // Highlight pre-selected checkboxes
	            if (checkbox.prop("checked"))
	                checkbox.parent().addClass("multiselect-on");
	 
	            // Highlight checkboxes that the user selects
	            checkbox.click(function() {
	                if (checkbox.prop("checked"))
	                    checkbox.parent().addClass("multiselect-on");
	                else
	                    checkbox.parent().removeClass("multiselect-on");
	            });
	        });
	    });
	    
		this.selections = function() {
			var result = [];
			$(this).find(".multiselect-on").each(function(key, value) {
				result.push($(this).attr("id"));
			});
			return result;
		};

		return this;
	};	
	$.fn.inv_pagetitle = function() {
		$(this).addClass("page-title");
		var span = $("<span/>")
		span.html($(this).html());
		$(this).html("");
		$(this).append(span);
		return this;
	};
	
	$.fn.inv_list = function(model) {
		$(this).addClass("inv-list");
		$(this).find("div").addClass("inv-list");
		this.select = function(id) {
			$(this).find("div[id='" + id + "']").addClass("row-selected");
		};
		return this;
	};

	$.fn.inv_table = function(model) {
		$(this).addClass("inv-table");
		
		$(this).find("tr").hover(
				function() {
					$(this).find("td").addClass("row-hover");
				},
				function() {
					$(this).find("td").removeClass("row-hover");
				}

		);
		
		$(this).find("tr").click(
			function(event) {
				var selected = $(this).hasClass("row-selected");
				console.log("selected=" + selected);
				if(!event.ctrlKey) {
					$(this).parent().find("tr").removeClass("row-selected");
				}
				if(selected) {
					$(this).removeClass("row-selected");
					console.log("de-selecting");
				} else {
					$(this).addClass("row-selected");
					console.log("selecting");
				}
			}
		);
		
		this.selections = function() {
			var result = [];
			$(this).find(".row-selected").each(function(key, value) {
				result.push($(this).attr("id"));
			});
			return result;
		};
		
		this.selectedNodes = function() {
			return $(this).find(".row-selected");
		};
		
		
		this.selected = function() {
			return $(this).find("tr.row-selected").length;
		};
		
		return this;
	};

}( jQuery ));

$(function() {
	$("[data-en-type='accordian-button']").click(function(e) {
		e.preventDefault();
		var $this = $(this);
		if ($this.next().hasClass('show')) {
			$this.next().removeClass('show');
			$this.next().slideUp(350);
		} else {
			$this.parent().parent().find('li .accordion-inner').removeClass('show');
			$this.parent().parent().find('li .accordion-inner').slideUp(350);
			$this.next().toggleClass('show');
			$this.next().slideToggle(350);
		}
	});
	
	$("[data-en-type='page-title']").inv_pagetitle();
	$(".dual-selector").dualSelector();
});
