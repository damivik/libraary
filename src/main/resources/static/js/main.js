let book;
let shelf;
let addBookToShelfButtons = document.getElementsByClassName("btn-add-to-shelf");
let shelfButtons = document.getElementsByClassName("btn-shelves");

for (btn of addBookToShelfButtons) {
	btn.addEventListener("click", event => {
		book = event.target.id;
	});
}

for (btn of shelfButtons) {
	btn.addEventListener("click", event => {
		shelf = event.target.id;

		
		let token = $("meta[name='_csrf']").attr("content");
		
		const data = {
			bookshelfId: shelf,
			bookId: book
		};

		fetch('/addBook', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': token
			},
			body: JSON.stringify(data),
		})
			.then(response => {
				if (response.status >= 400) {
					throw new Error('Network response was not OK');
				}
				window.location.href = "/bookshelves/" + shelf;
			})
			.catch(error => {
				console.error('There has been a problem with your fetch operation:', error);
			});
	});
}

