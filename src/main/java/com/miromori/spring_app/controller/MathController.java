package com.miromori.spring_app.controller;

import com.miromori.spring_app.exceptions.CannotCalculeSquareRootOfNegativeNumber;
import com.miromori.spring_app.exceptions.DivisionByZeroException;
import com.miromori.spring_app.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");}
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiply(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("please set a numeric value");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");}
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");}
        if(numberTwo.equals("0")){
            throw new DivisionByZeroException("Cannot divide by zero");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double average(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a numeric value");}

        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping(value = "/sqrroot/{numberOne}", method = RequestMethod.GET)
    public Double sqrRoot(@PathVariable(value = "numberOne") String numberOne) throws Exception {
        if(!isNumeric(numberOne)){
            throw new UnsupportedMathOperationException("Please set a numeric value");}
        if(Integer.parseInt(numberOne) < 0){
            throw new CannotCalculeSquareRootOfNegativeNumber("Cannot calcule square root of negative number");
        }
        return Math.sqrt(Double.parseDouble(numberOne));
    }

    private boolean isNumeric(String str){
        if(str == null) return false;
        str = str.replace(",",".");
        try{
            Double.parseDouble(str);
        } catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    private Double convertToDouble(String str){
        if(str == null) return 0D;
        str = str.replace(",", ".");
        if(isNumeric(str)) return Double.parseDouble(str);
        return 0D;
    }
}
