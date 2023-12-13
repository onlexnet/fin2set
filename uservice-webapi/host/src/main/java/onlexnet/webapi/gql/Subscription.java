package onlexnet.webapi.gql;

import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import onlex.webapi.ViewGql;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
class Subscription {

  private final ReactiveSources reactiveSources;

  @SubscriptionMapping
  @SneakyThrows
  public Flux<ViewGql> view() {
    return reactiveSources.observed();
  }

}


// import java.time.Duration;
// import java.util.UUID;

// import javax.annotation.PostConstruct;

// import org.reactivestreams.Publisher;
// import org.springframework.context.event.EventListener;
// import org.springframework.stereotype.Component;

// import graphql.kickstart.tools.GraphQLSubscriptionResolver;
// import lombok.Value;
// import npl.bvs.notifications.GenericProgressNotification;
// import reactor.core.publisher.EmitterProcessor;
// import reactor.core.publisher.Flux;

// /** Implementation of subscriptions defined in Broker.graphqls file. */
// @Component
// public class ProgressSubscription implements GraphQLSubscriptionResolver, AutoCloseable {


//     private EmitterProcessor<OperationProgress> source = EmitterProcessor.create(1);
//     private static final int BUFFERRING_TIMEOUT = 100;
//     private Flux<OperationProgress> observable = source.sample(Duration.ofMillis(BUFFERRING_TIMEOUT));

//     /**
//      * This method will filter Notifications to proper listeners.
//      * @param correlationId Listener correlationId.
//      * @return Filtered notifications.
//     */
//     public Publisher<OperationProgress> operationProgress(UUID correlationId) {
//         return observable.filter(item -> item.correlationId.equals(correlationId));
//     }

//     /**
//      * Post-construct method. please invoke manually in tests where Spring is not creating the Component.
//      */
//     @PostConstruct
//     public void init() {
//         // We open first subscription so emitter can have minimum one listener.
//         // Without that trick, without first listener Emitter will block after some time when internal
//         // buffer is full and will stop working when the last subscriber is off.
//         // Because the emitter is now shared, we would like to have it working independently of subscribers
//         // so we keep one subscriber alive.
//         observable.subscribe();
//     }

//     /**
//      * Releasing resources owned by the component.
//      */
//     @Override
//     public void close() {
//         source.onComplete();
//     }

//     /**
//      * Converts Notification to something more appropriate for Graphql.
//      * @param notification GenericProgressNotification.
//      */
//     @EventListener
//     void on(GenericProgressNotification notification) {
//         var signal = new OperationProgress(
//             notification.getCorrelationId(),
//             notification.getNameOfProgressingStep(),
//             notification.getProgress(),
//             notification.getTotal());
//         source.onNext(signal);
//     }

//     @Value
//     public static class OperationProgress {
//         private UUID correlationId;
//         private String stepCategory;
//         private int currentStep;
//         private int totalSteps;
//     }

// }
