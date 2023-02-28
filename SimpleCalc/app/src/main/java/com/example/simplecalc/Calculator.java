package com.example.simplecalc;

    public class Calculator {



        // Available operations
        public enum Operator {ADD, SUB, DIV, MUL, POWER}

        /**
         * Addition operation
         */
        public double add(double firstOperand, double secondOperand) {
            return firstOperand + secondOperand;
        }

        /**
         * Subtract operation
         */
        public double sub(double firstOperand, double secondOperand) {
            return firstOperand - secondOperand;
        }

        /**
         * Divide operation
         */
        public double div(double firstOperand, double secondOperand) {
            return firstOperand / secondOperand;
        }

        /**
         * Multiply operation
         */
        public double mul(double firstOperand, double secondOperand) {
            return firstOperand * secondOperand;
        }

        /**
         * Power operation
         */

        public double power(double firstOperand, double secondOperand) {

            return Math.pow(firstOperand, secondOperand);
        }

    }