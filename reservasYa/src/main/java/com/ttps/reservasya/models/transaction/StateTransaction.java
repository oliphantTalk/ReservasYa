package com.ttps.reservasya.models.transaction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Polymorphism(type = PolymorphismType.IMPLICIT)
@DiscriminatorColumn(name = "STATE")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PendingTransaction.class, name= "PENDING"),
        @JsonSubTypes.Type(value = StartedTransaction.class, name= "STARTED"),
        @JsonSubTypes.Type(value = FinishedTransaction.class, name= "FINISHED"),
        @JsonSubTypes.Type(value = CancelledTransaction.class, name= "CANCELLED"),
        @JsonSubTypes.Type(value = RollbackedTransaction.class, name= "ROLLBACKED"),
        @JsonSubTypes.Type(value = PausedTransaction.class, name= "PAUSED"),
})
public abstract class StateTransaction implements Serializable {


    protected Long id;
    protected TransactionStates type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionStates getType() {
        return type;
    }

    public void setType(TransactionStates type) {
        this.type = type;
    }

    public static StateTransaction start(){
        return new PendingTransaction();
    }

    public  void doStart(Transaction transaction){};
    public  void doCancel(Transaction transaction){};
    public  void doFinish(Transaction transaction){};
    public  void doPause(Transaction transaction){};
    public  void doRollBack(Transaction transaction){};
}

