package org.shimomoto.blog.pttdg

import org.shimomoto.blog.pttdg.generator.Generator
import spock.lang.Specification

import java.time.LocalDate


class BookSpec extends Specification {

	Generator gen
	Generator gen2
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
		gen = new Generator(s1)
		gen2 = new Generator(s1)
		og = new Generator(s2)
		r1 = new Random(s3)
		r2 = new Random(s3)
		o1 = new Random(s4)
	}

	def "SANITY - getBook() fields close inspection"() {
		given:
		def titleRegex = ~/(:?[A-Z][a-z]{0,14}\s?){1,5}/
		def authorRegex = ~/(:?[A-Z][a-z]{0,14}\s?){1,3}/
		def start = LocalDate.of(2015,1,1)
		def end = LocalDate.of(2022,10,31)

		when:
		def results = (1i..2_000i).collect {gen.getBook() }

		then:
		results*.title.findAll { it ==~ titleRegex}.size() == 2_000i
		results*.author.findAll { it ==~ authorRegex}.size() == 2_000i
		results*.release.findAll { it >= start && it < end}.size() == 2_000i
		results*.price.findAll { it > 0.0d && it < 1_000d }.size() == 2_000i
		results*.margin.findAll { it > 0.0d && it < 1.0d }.size() == 2_000i
		results*.stockQuantity.findAll { it >= 1i && it < 400i}.size() == 2_000i
	}

	def "SANITY - getBook() is pseudo random"() {
		expect:
		gen.getBook() == gen2.getBook()
		gen.getBook(r1) == gen2.getBook(r2)
	}

	def "SANITY - getBook() counter proof is pseudo random"() {
		expect:
		gen.getBook() != og.getBook()
		gen2.getBook(r1) != og.getBook(o1)
	}

	def "askingPrice works"() {
		given: "some book with price and margin"
		def b = gen.getBook().toBuilder()
		.price(100.0d)
		.margin(0.3d)
		.build()

		expect: "asking price calculation, rounded down is correct"
		b.calculateAskingPrice().trunc() == 130
	}
}
