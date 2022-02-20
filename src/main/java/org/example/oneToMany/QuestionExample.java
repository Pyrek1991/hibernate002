package org.example.oneToMany;

import org.example.oneToMany.model.Answer;
import org.example.oneToMany.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Question question = new Question("ile to 5 + 5?");
        Answer a1 = new Answer("8", false);
        Answer a2 = new Answer("10", true);
        Answer a3 = new Answer("11", false);
        Answer a4 = new Answer("15", false);

        // dodanie odpowiedzi do Question
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(a1);
        answers1.add(a2);
        answers1.add(a3);
        answers1.add(a4);
        question.setAnswers(answers1);
        //session.save(question);

        Question q2 = new Question("ile jest 4 - 4?");
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("4", false));
        answers2.add(new Answer("14", false));
        answers2.add(new Answer("0", true));
        answers2.add(new Answer("5", false));
        q2.setAnswers(answers2);
        //session.save(q2);

        // odczytanie informacji z bazy
        Query query = session.createQuery("FROM Question");
        List<Question> questions = query.list();
        questions.stream().forEach(
                q -> {
                    // odczyt pytań
                    System.out.println("\n" + q + ":");
                    // odczyt odpowiedzi
                    q.getAnswers().stream().forEach(a -> System.out.println("-" + a));
                }
        );

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}