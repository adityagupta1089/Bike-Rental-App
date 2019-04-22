package com.csl456.bikerentalapp.core;

public class Pair<T, U> {
	T first;
	U second;
	

	public Pair() {
	}

	public Pair(T t, U u) {
		this.first = t;
		this.second = u;
	}

	public T getT() {
		return first;
	}

	public void setT(T t) {
		this.first = t;
	}

	public U getU() {
		return second;
	}

	public void setU(U u) {
		this.second = u;
	}

}
