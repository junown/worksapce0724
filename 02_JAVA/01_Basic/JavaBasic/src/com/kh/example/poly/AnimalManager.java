package com.kh.example.poly;

public class AnimalManager {

	public static void main(String[] args) {
		Animal[] animalArr = new Animal[5];
		animalArr[0] = new Dog("진돗개" ,11, "말티즈");
		animalArr[1] = new Cat("깜장이", 11, "검은색");
		animalArr[2] = new Dog("비비", 1, "진돗개");
		animalArr[3] = new Cat("깜이", 4, "검은색");
		animalArr[4] = new Cat("깜비", 1, "검은색");
		
		for (Animal ani : animalArr) {
			ani.speak();
			if(ani instanceof Dog) {
				String breed = ((Dog)ani).getBreed();
				System.out.println("이 강아지의 견종은 "+breed+"입니다");
			}else if (ani instanceof Cat) {
				String color = ((Cat)ani).getColor();
				System.out.println("이 고양이의 색상은 "+color+"입니다");
			}
		}
	}

}