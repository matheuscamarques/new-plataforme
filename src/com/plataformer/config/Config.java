package com.plataformer.config;


public enum Config{
    WIDTH(1080), HEIGHT(700);

    private Config(Integer value){
       this.value = value;
     }

     private Integer value;
     public Integer getValue() {
         return this.value;
     }
     
     public String toString(){
       return this.value.toString();
     } 
}



