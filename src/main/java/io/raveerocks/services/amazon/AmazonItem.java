package io.raveerocks.services.amazon;

public final class AmazonItem {

    private String name;
    private String price;
    private String link;

    public AmazonItem() {
        name = "Not Available";
        price = "Not Available";
        link = "Not Available";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "AmazonItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
                '}' + "\n";
    }
}
