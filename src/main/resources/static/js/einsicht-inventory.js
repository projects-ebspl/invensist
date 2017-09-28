(function ( $ ) {
	$.fn.tag = function() {
		return this.prop("tagName");
	};
	
	$.fn.inv_pagetitle = function() {
		$(this).addClass("page-title");
		var span = $("<span/>")
		span.html($(this).html());
		$(this).html("");
		$(this).append(span);
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
});
