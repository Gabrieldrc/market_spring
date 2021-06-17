package com.gdrc.market.persistence.entity;

import javax.persistence.*;

/*
@Entity:
    Esta anotacion le hace entender a Java que esta clase
    se comportara como una clase que mapea una tabla de
    la base de datos.
@Table:
    Indicamos que clase Product referencia a la tabla
    "products" de la db.
@Colum:
    Cada vez que le cambiemos el nombre de la columna
    hay que indicar el nombre real que esta en la tabla.
    Y se cambio porque en java no es buena practica usar
    separador de piso sino camelcase.
@Id:
    Es para indicar que es la clave primaria (y es sencilla)
@GeneratedValue:
    Como se genera automaticamente se coloca esta anotacion
    con la estrategia para indicar que se genere de tipo
    la clave primaria.
@ManyToOne:
    Es una anotacion que indica la relacion que tiene
    Product con Category.
@JoinColumn:
    Es la anotacion que indica por que atributo estan
    relacionados, que en este caso es "category_id",
    ademas le indicamos con "insertable = false, updatable = false"
    que con esta relacion no vamos a borrar, cambiar ni actualizar
    una nueva categoria, eso se haria directamente con el category.
 */
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    private String name;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity categoryEntity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
