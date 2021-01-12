package com.gmail.yauhen2012.service.model;

import java.util.Objects;

public class CrawlerDTO {

    private String seed;
    private String terms;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CrawlerDTO that = (CrawlerDTO) o;
        return Objects.equals(seed, that.seed) && Objects.equals(terms, that.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seed, terms);
    }

}
