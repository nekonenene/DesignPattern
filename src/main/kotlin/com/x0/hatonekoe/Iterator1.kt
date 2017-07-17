package com.x0.hatonekoe

class Iterator1 {
    fun main() {
        val bookShelf = BookShelf(3)
        bookShelf.addBook(Book("なまずさんの冒険"))
        bookShelf.addBook(Book("福沢諭吉の人生"))
        bookShelf.addBook(Book("お金欲しい"))

        val iterator = BookShelfIterator(bookShelf)
        while (iterator.hasNext()) {
            val book = iterator.next()
            println(book?.name)
        }
    }
}

interface Iterator {
    fun hasNext(): Boolean
    fun next(): Book?
}

interface Aggregate {
    fun iterator(): Iterator
}

class BookShelfIterator(val bookShelf: BookShelf): Iterator {
    private var index = 0

    override fun next(): Book? {
        return bookShelf.getBookAt(index++)
    }

    override fun hasNext(): Boolean {
        return (index < bookShelf.getLength())
    }

}

class Book(val name: String)

class BookShelf(maxSize: Int): Aggregate {
    private var books: Array<Book?> = arrayOfNulls<Book>(maxSize)
    private var last = 0

    override fun iterator(): Iterator {
        return BookShelfIterator(this)
    }

    fun getBookAt(index: Int): Book? {
        return books[index]
    }

    fun addBook(book: Book) {
        books[last] = book
        ++last
    }

    fun getLength(): Int {
        return last
    }
}
