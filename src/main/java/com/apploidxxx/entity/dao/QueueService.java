package com.apploidxxx.entity.dao;

import com.apploidxxx.entity.Queue;

import java.util.List;

/**
 * @author Arthur Kupriyanov
 */
public class QueueService {
    private QueueDAO queueDAO = new QueueDAO();

    public QueueService() {
    }

    public Queue findQueue(String queueName) {
        return queueDAO.findById(queueName);
    }

    public void saveQueue(Queue queue) {
        queueDAO .save(queue);
    }

    public void deleteQueue(Queue queue) {
        queueDAO .delete(queue);
    }

    public void updateQueue(Queue queue) {
        queueDAO .update(queue);
    }

    public List<Queue> findAllQueues() {
        return queueDAO .findAll();
    }
}
