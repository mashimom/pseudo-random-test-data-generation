package org.shimomoto.blog.pttdg.generator

import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class GeneratorSpec extends Specification {

	Generator g1
	Generator g2
	Generator og
	Random r1
	Random r2
	Random o1

	void setup() {
		def random = new Random()
		def s1 = random.nextLong()
		def s2 = random.nextLong()
		def s3 = random.nextLong()
		def s4 = random.nextLong()
		g1 = new Generator(s1)
		g2 = new Generator(s1)
		og = new Generator(s2)
		r1 = new Random(s3)
		r2 = new Random(s3)
		o1 = new Random(s4)
	}

	def "sanity - proves pseudo random"() {
		expect:
		this.r1.nextInt() == this.r2.nextInt()
		this.r1.ints().limit(1000L).toArray() == this.r2.ints().limit(1000L).toArray()
		this.r2.nextInt() != this.o1.nextInt()
	}

	def "getNumberChars works"() {
		expect:
		g1.getNumberChars() == g2.getNumberChars()
		g1.getNumberChars(300L) == g2.getNumberChars(300L)
		g1.getNumberChars(10L, r1) == g2.getNumberChars(10L, r2)
	}

	def "getNumberChars fails"() {
		expect:
		g1.getNumberChars(30L) != og.getNumberChars(30L)
		g2.getNumberChars(30L) != og.getNumberChars(30L, o1)
	}

	def "getUpperChars works"() {
		expect:
		g1.getUpperChars() == g2.getUpperChars()
		g1.getUpperChars(300L) == g2.getUpperChars(300L)
		g1.getUpperChars(10L, r1) == g2.getUpperChars(10L, r2)
	}

	def "getUpperChars fails"() {
		expect:
		g1.getUpperChars(30L) != og.getUpperChars(30L)
		g2.getUpperChars(30L) != og.getUpperChars(30L, o1)
	}

	def "getLowerChars works"() {
		expect:
		g1.getLowerChars() == g2.getLowerChars()
		g1.getLowerChars(300L) == g2.getLowerChars(300L)
		g1.getLowerChars(10L, r1) == g2.getLowerChars(10L, r2)
	}

	def "getLowerChars fails"() {
		expect:
		g1.getLowerChars(30L) != og.getLowerChars(30L)
		g2.getLowerChars(30L) != og.getLowerChars(30L, o1)
	}

	def "getWord works"() {
		expect:
		g1.getWord() == g2.getWord()
		g1.getWord(r1) == g2.getWord(r2)
	}

	def "getWord fails"() {
		expect:
		g1.getWord() != og.getWord()
		g2.getWord(r1) != og.getWord(o1)
	}


	def "getCapitalizedWord works"() {
		expect:
		g1.getCapitalizedWord() == g2.getCapitalizedWord()
		g1.getCapitalizedWord(r1) == g2.getCapitalizedWord(r2)
	}

	def "getCapitalizedWord fails"() {
		expect:
		g1.getCapitalizedWord() != og.getCapitalizedWord()
		g2.getCapitalizedWord(r1) != og.getCapitalizedWord(o1)
	}

	def "getTitle works"() {
		expect:
		g1.getTitle() == g2.getTitle()
		g1.getTitle(r1) == g2.getTitle(r2)
	}

	def "getTitle fails"() {
		expect:
		g1.getTitle() != og.getTitle()
		g2.getTitle(r1) != og.getTitle(o1)
	}

	def "getAuthor() works"() {
		expect:
		g1.getAuthor() == g2.getAuthor()
		g1.getAuthor(r1) == g2.getAuthor(r2)
	}

	def "getAuthor() fails"() {
		expect:
		g1.getAuthor() != og.getAuthor()
		g2.getAuthor(r1) != og.getAuthor(o1)
	}

	def "getMills() works"() {
		given:
		def startDate = Instant.now().minus(32, ChronoUnit.DAYS).toEpochMilli()
		def endDate = Instant.now().plus(32, ChronoUnit.DAYS).toEpochMilli()
		expect:
		g1.getMills() == g2.getMills()
		g1.getMills(endDate) == g2.getMills(endDate)
		g1.getMills(endDate, startDate) == g2.getMills(endDate, startDate)
		g1.getMills(endDate, startDate, r1) == g2.getMills(endDate, startDate, r2)
	}

	def "getMills() fails"() {
		given:
		def startDate = Instant.now().minus(32, ChronoUnit.DAYS).toEpochMilli()
		def endDate = Instant.now().plus(32, ChronoUnit.DAYS).toEpochMilli()
		expect:
		g1.getMills() != og.getMills()
		g1.getMills(endDate) != og.getMills(endDate)
		g1.getMills(endDate, startDate) != og.getMills(endDate, startDate)
		g2.getMills(endDate, startDate, r1) != og.getMills(endDate, startDate, o1)
	}

	def "getDate() works"() {
		given:
		def startDate = LocalDate.ofInstant(Instant.now().minus(32, ChronoUnit.DAYS), ZoneId.systemDefault())
		def endDate = LocalDate.ofInstant(Instant.now().plus(32, ChronoUnit.DAYS), ZoneId.systemDefault())
		expect:
		g1.getDate() == g2.getDate()
		g1.getDate(endDate) == g2.getDate(endDate)
		g1.getDate(endDate, startDate) == g2.getDate(endDate, startDate)
		g1.getDate(endDate, startDate, r1) == g2.getDate(endDate, startDate, r2)
	}

	def "getDate() fails"() {
		given:
		def startDate = LocalDate.ofInstant(Instant.now().minus(32, ChronoUnit.DAYS), ZoneId.systemDefault())
		def endDate = LocalDate.ofInstant(Instant.now().plus(32, ChronoUnit.DAYS), ZoneId.systemDefault())
		expect:
		g1.getDate() != og.getDate()
		g1.getDate(endDate) != og.getDate(endDate)
		g1.getDate(endDate, startDate) != og.getDate(endDate, startDate)
		g2.getDate(endDate, startDate,r1) != og.getDate(endDate, startDate, o1)
	}

	def "getBook() works"() {
		expect:
		g1.getBook() == g2.getBook()
		g1.getBook(r1) == g2.getBook(r2)
	}

	def "getBook() fails"() {
		expect:
		g1.getBook() != og.getBook()
		g2.getBook(r1) != og.getBook(o1)
	}
}
