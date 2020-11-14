package com.epam.community.z.spring.testing.post.stat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticsCollectorAspect {
    private Map<Integer, Integer> stats = new HashMap<>();

    @Before("execution(* com.epam.community.z.spring.testing.post.PostService.findOne(..))")
    public void collectStat(JoinPoint joinPoint) {
        final int postId = (int) joinPoint.getArgs()[0];
        stats.compute(postId, (key, oldValue) -> {
            if (oldValue == null) {
                oldValue = 0;
            }
            return (oldValue + 1);
        });
    }

    public int getVisits(int postId) {
        return stats.getOrDefault(postId, 0);
    }
}
