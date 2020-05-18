package model;

import java.time.LocalDateTime;

import com.lynden.gmapsfx.javascript.object.LatLong;

public class Product {

	private int id;
	private String name;
	private Double price;
	private Double weight;
	private String picture;
	private LocalDateTime dealAvaiableFrom;
	private LocalDateTime dealAvaiableTill;
	private LatLong coordinates;

	Product(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.price = builder.price;
		this.weight = builder.weight;
		this.picture = builder.picture;
		this.dealAvaiableFrom = builder.dealAvaiableFrom;
		this.dealAvaiableTill = builder.dealAvaiableTill;
		this.coordinates = builder.coordinates;
	}

	
	
	public static class Builder {
		private int id;
		private String name;
		private Double price;
		private Double weight;
		private String picture;
		private LocalDateTime dealAvaiableFrom;
		private LocalDateTime dealAvaiableTill;
		private LatLong coordinates;

		/*public Builder(int Id, String productName, Double price, Double weight,
				String productPicture, LocalDateTime dealAvaiableFrom, LocalDateTime dealAvaiableTill,
				LatLong coordinates) {

			this.id = id;
			this.productName = productName;
			this.price = price;
			this.weight = weight;
			this.picture = picture;
			this.dealAvaiableFrom = dealAvaiableFrom;
			this.dealAvaiableTill = dealAvaiableTill;
			this.coordinates = coordinates;
		}
*/
		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder price(Double price) {
			this.price = price;
			return this;
		}

		public Builder weight(Double weight) {
			this.weight = weight;
			return this;
		}
		
		public Builder picture(String picture) {
			this.picture = picture;
			return this;
		}

		public Builder dealAvaiableFrom(LocalDateTime dealAvaiableFrom) {
			this.dealAvaiableFrom = dealAvaiableFrom;
			return this;
		}
		
		public Builder dealAvaiableTill(LocalDateTime dealAvaiableTill) {
			this.dealAvaiableTill = dealAvaiableTill;
			return this;
		}
		
		public Builder coordinates(LatLong coordinates) {
			this.coordinates = coordinates;
			return this;
		}
		public Product build() {
			Product product = new Product(this);
			return product;
		}
	}
	
	
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}

	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	public Double getPrice() {return this.price;}
	public void setProductPrice(Double price) {this.price = price;}
	
	public Double getWeigh() {return this.weight;}
	public void setWeigh(Double weight) {this.weight = weight;}
	
	public String getPicture() {return this.picture;}
	public void setPicture(String picture) {this.picture = picture;}
	
	public LocalDateTime getDealAvaiableFrom() {return this.dealAvaiableFrom;}
	public void setDealAvaiableFrom(LocalDateTime dealAvaiableFrom) {this.dealAvaiableFrom = dealAvaiableFrom;}
	
	public LocalDateTime getDealAvaiableTill() {return this.dealAvaiableTill;}
	public void setDealAvaiableTill(LocalDateTime dealAvaiableTill) {this.dealAvaiableTill = dealAvaiableTill;}
	
	public LatLong getCoordinates() {return this.coordinates;}
	public void setCoordinates(LatLong coordinates) {this.coordinates = coordinates;}
	
	
}
