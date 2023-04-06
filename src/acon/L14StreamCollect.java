package acon;

import javax.print.attribute.standard.PrinterURI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

enum Category{
    식품,의류,가구,문구류,침구류
}
class Product{
    private String name; //중복되는 이름이 없음
    private int price; //가격
    private Category category; //Category :식품,의류,가구,문구류,침구류
    private int quantity=10; //수량
    public Product(String name,int price, Category category) {
        this.setName(name);
        this.price=price;
        this.category=category;
    }
    public Product(String name,int price, Category category, int quantity) {
        this.setName(name);
        this.price=price;
        this.category=category;
        this.quantity=quantity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.name+"("+this.price+"원"+", "+this.quantity+"개"+")";
    }
}

public class L14StreamCollect {
    public static void main(String[] args) {
        //collect 의 partitioningBy , groupingBy (return Map) 로 분류를 해보자!
        //잡화점 운영 (상품 등록)
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("더블샷",1500,Category.식품,12));
        productList.add(new Product("허쉬 초콜릿", 1000, Category.식품));
        productList.add(new Product("허쉬 초콜릿 우유", 1200, Category.식품,5));
        productList.add(new Product("바나나 우유", 1400, Category.식품,0));
        productList.add(new Product("돼지바", 900, Category.식품,15));
        productList.add(new Product("하겐다즈 바닐라", 5000, Category.식품,11));
        productList.add(new Product("소세지 도시락", 3500, Category.식품,3));
        productList.add(new Product("도시락 컵라면", 800, Category.식품,50));
        productList.add(new Product("hp 연필", 500, Category.문구류,200));
        productList.add(new Product("지우개", 500, Category.문구류,50));
        productList.add(new Product("볼펜", 1000, Category.문구류,100));
        productList.add(new Product("화이트 지우개", 2500, Category.문구류,3));
        productList.add(new Product("흰색 셔츠", 10000, Category.의류,0));
        productList.add(new Product("나이키 반팔 티", 25000, Category.의류,5));
        productList.add(new Product("나이키 신발", 110000, Category.의류,2));
        productList.add(new Product("아디다스 신발", 95000, Category.의류,2));
        productList.add(new Product("여름용 이불", 25000, Category.침구류,1));
        productList.add(new Product("솜 베게", 2000, Category.침구류,10));
        productList.add(new Product("대나무 베게", 2000, Category.침구류,0));
        productList.add(new Product("침대 커버 퀸", 15000, Category.침구류,3));
        productList.add(new Product("행거", 60000, Category.가구,1));
        productList.add(new Product("책상", 45000, Category.가구,1));
        productList.add(new Product("듀오백의자", 145000, Category.가구,3));

        System.out.println(productList);
        //collect(Collectors.groupingBy(Function:(Element e)-> e.key) : key를 Category, Value를 해당 카테고리의 상품리스트로 하는 Map이 반환된다.
        Map<Category,List<Product>> cateGroupList = productList.stream()
                .collect(Collectors.groupingBy((p2)->p2.getCategory()));
        System.out.println(cateGroupList);

        System.out.println(cateGroupList.get(Category.문구류));

        //collect(partitioningBy(Predicate : (Element e)->조건식)) : true,false 로 나눈 상품리스트 그룹이 반환
        // 재고가 있는 (true)는 그룹과 없는 (false) 그룹으로 나누세요.
        Map<Boolean,List<Product>> partitionList = productList.stream().collect(Collectors.partitioningBy((e)->e.getQuantity()>0));

        System.out.println(partitionList);
        System.out.println(partitionList.get(false));
        System.out.println(partitionList.get(true));

    }
}
