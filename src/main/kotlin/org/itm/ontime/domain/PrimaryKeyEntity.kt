package org.itm.ontime.domain

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Transient
import lombok.Getter
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.Objects
import java.util.UUID

@MappedSuperclass
@Getter
abstract class PrimaryKeyEntity : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid();

    @Transient
    private var _isNew = true

    override fun getId(): UUID = id

    override fun isNew(): Boolean = _isNew

    override fun hashCode() = Objects.hashCode(id)

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }
        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any? { // TODO : Any -> Serializable
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as PrimaryKeyEntity).id
        }
    }

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

}