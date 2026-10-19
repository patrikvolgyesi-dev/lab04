package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_DecreasesTorpedoCount() {
        // Arrange
        TorpedoStore store = new TorpedoStore(3, 0.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_AlwaysFailingStore_ReturnsFalseAndKeepsTorpedoes() {
        // Arrange: 1.0 failure rate = always fails
        TorpedoStore store = new TorpedoStore(3, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert: firing failed and no torpedo was used
        assertEquals(false, result);
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(3);

        // Act + Assert: firing 0 torpedoes is invalid
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(2);

        // Act + Assert: only 2 torpedoes, cannot fire 3
        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
    }
}