package org.shimomoto.blog.pttdg.generator

import org.shimomoto.blog.pttdg.model.Book

import java.time.LocalDate

class Generator {
	public static final long MAX_EPOCH = 365241780471L
	final long seed
	final Random random
	static def nums = (48..57)
	static def upper = (65..90)
	static def lower = (97..122)

	Generator(long seed = new Random().nextLong()) {
		this.seed = seed
		this.random = new Random(seed)
		println("Using Generator seed= ${seed}")
	}

	Book getBook(Random random = this.random) {
		def maxDate = LocalDate.of(2022, 10, 31)
		def minDate = LocalDate.of(2015, 1, 1)
		Book.builder()
				.title(getTitle(random))
				.author(getAuthor(random))
				.release(getDate(maxDate, minDate,random))
				.price(random.nextDouble(1_000.0))
				.margin(random.nextDouble(1.0))
				.stockQuantity(random.nextInt(1,400))
				.build()
	}

	private char[] getChars(def charCodeRange, long length = 1L, Random random = this.random) {
		random.ints(charCodeRange.fromInt, charCodeRange.toInt)
				.limit(length)
				.toArray() as char[]
	}

	private char[] getNumberChars(long length = 1L, Random random = this.random) {
		getChars(nums, length, random)
	}

	private char[] getUpperChars(long length = 1L, Random random = this.random) {
		getChars(upper, length, random)
	}

	private char[] getLowerChars(long length = 1L, Random random = this.random) {
		getChars(lower, length, random)
	}

	private char[] getWord(Random random = this.random) {
		getLowerChars(random.nextInt(1, 15), random)
	}

	private String getCapitalizedWord(Random random = this.random) {
		(
				getUpperChars(1, random).toList() +
						getLowerChars(random.nextInt(14), random).toList()
		).join()
	}

	private String getTitle(Random random = this.random) {
		(1..random.nextInt(1, 5)).collect {
			getCapitalizedWord(random)
		}.join(" ")
	}

	private String getAuthor(Random random = this.random) {
		(1..random.nextInt(1, 3)).collect {
			getCapitalizedWord(random)
		}.join(" ")
	}

	private long getMills(long max = MAX_EPOCH, long min = 0, Random random = this.random) {
		random.nextLong(min<0?0:min,max)
	}

	private LocalDate getDate(LocalDate max = LocalDate.ofEpochDay(MAX_EPOCH), LocalDate min = LocalDate.ofEpochDay(0L), Random random = this.random) {
		LocalDate.ofEpochDay(getMills(max.toEpochDay(),min.toEpochDay(), random))
	}
}
