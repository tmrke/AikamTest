package ru.ageev.exception;

public class NotFoundResultCriteriaException extends  Exception {
            private final String message = "Не найдено результатов, удовлетвояющих критериям";

        @Override
        public String getMessage() {
            return message;
        }
    }


