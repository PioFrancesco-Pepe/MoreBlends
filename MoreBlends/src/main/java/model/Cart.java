package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Cart implements Serializable {

private static final long serialVersionUID = 1L;
private List<Prodotto> products;
private List<Integer> quantita;

	public Cart() {
		products = new ArrayList<>();
		quantita= new ArrayList<>();
	}
	
	public void addProduct(Prodotto product,Integer quantita,String s) {
		if(checkProduct(product))
			updateCart(product, quantita,s);
		else {
			products.add(product);
			this.quantita.add(quantita);
		}
	}
	
	public void deleteProduct(Prodotto product,Integer quantita) {
	
		int i=0;
		while(i<products.size()) {
			if(products.get(i).getId() == product.getId())
			{
					products.remove(products.get(i));
					this.quantita.remove(quantita);
			}
			i++;
		}
	}
	
	public List<Prodotto> getProducts() {
		return  products;
	}
	
	public List<Integer> getQuantita(){
		return quantita;
	}

	public void updateCart(Prodotto product, Integer quant,String s) {
		if(checkProduct(product) && s.equalsIgnoreCase("addC"))
		{
			Iterator<Prodotto> p= products.iterator();
			int i=0;
			while(p.hasNext()) {
				Prodotto tempp=p.next();
				if(tempp.getId()==product.getId())
					this.quantita.set(i, quant+quantita.get(i));
				i++;
			}
		}else {		
		Iterator<Prodotto> p= products.iterator();
		int i=0;
		while(p.hasNext()) {
			Prodotto tempp=p.next();
			if(tempp.getId()==product.getId())
				this.quantita.set(i, quant);
			i++;
		}
	}
	}
	
	private boolean checkProduct(Prodotto product)
	{
		Iterator<Prodotto> p= products.iterator();
		
		while(p.hasNext()) {
			Prodotto temp=p.next();
			if(temp.getId()==product.getId())
				return true;
		}
			return false;
	}	
	
	public boolean isEmpty()
	{
		return (products.isEmpty() && quantita.isEmpty());
	}
}
