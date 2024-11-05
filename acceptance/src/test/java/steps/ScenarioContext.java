package steps;

import io.restassured.response.Response;

import java.util.ArrayDeque;
import java.util.Deque;

public class ScenarioContext {
    public final Deque<Response> responses = new ArrayDeque<>();
}
