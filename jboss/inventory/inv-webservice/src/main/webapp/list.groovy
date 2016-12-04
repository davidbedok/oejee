println '<!DOCTYPE html>'
html.html {
	head {
		meta('http-equiv': 'Content-Type', content: 'text/html; charset=UTF-8')
		link(rel: 'stylesheet', type: 'text/css', href: 'style/page.css')
		title(':: Groovy ::')
	}
	body {
		h1('List of Inventory (books)')
		if ( request.getAttribute('inventories').isEmpty() ) {
			span('Inventory list is empty.')
		} else {
			table(class: 'inventorytable') {
				thead {
					tr {
						th('Reference')
						th('Name')
						th('Type')
						th('Value')
					}
				}
				tbody {
					request.getAttribute('inventories').each { item ->
						tr {	
							td(item.reference)
							td(item.name)
							td(item.type)
							td(item.value)
						}
					}
				}
			}
		}
	}
}