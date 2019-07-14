package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME){

    fun askQuestion(): String = when (question) {
                Question.NAME -> Question.NAME.question
                Question.PROFESSION -> Question.PROFESSION.question
                Question.MATERIAL -> Question.MATERIAL.question
                Question.BDAY -> Question.BDAY.question
                Question.SERIAL -> Question.SERIAL.question
                Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>>{
        return if (question == Question.IDLE){
            "${question.question}" to status.color
        }
        else if (question.answers.contains(answer.trim())){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
            if(status == Status.CRITICAL){
                question = Question.NAME
                status = Status.NORMAL
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }else{
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }

        }
    }

    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus(): Status {
            if(this.ordinal < values().lastIndex){
                return values()[this.ordinal + 1]
            }else {
                return values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("бендер","bender")) {
            override fun falidation(answer: String): Boolean = answer.trim().firstOrNull()?.isUpperCase()?: false
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun falidation(answer: String): Boolean = answer.trim().firstOrNull()?.isLowerCase()?: false

            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал","дерево","metal","iron","wood")){
            override fun falidation(answer: String): Boolean = answer.trim().contains(Regex("\\d")).not()
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun falidation(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]*\$"))

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun falidation(answer: String): Boolean = answer.trim().contains(Regex("^[0-9]{7}\$"))

            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun falidation(answer: String): Boolean = true

            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
        abstract fun falidation(answer: String): Boolean

    }

}