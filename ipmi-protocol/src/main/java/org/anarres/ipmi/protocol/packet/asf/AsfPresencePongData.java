/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.anarres.ipmi.protocol.packet.asf;

import com.google.common.collect.Iterables;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nonnull;
import org.anarres.ipmi.protocol.packet.common.Bits;

/**
 * PresencePong.
 * 
 * http://www.dmtf.org/sites/default/files/standards/documents/DSP0136.pdf
 * http://www.dmtf.org/standards/asf
 * Section 3.2.4.3 page 36.
 *
 * @author shevek
 */
public class AsfPresencePongData extends AbstractAsfData {

    public enum SupportedEntity implements Bits.Wrapper {

        IPMI_SUPPORTED(Bits.forBitIndex(0, 7)),
        SUPPORT_ASF_V1(Bits.forBinaryBE(0, 3, 4, 0b0001));
        private final Bits bits;

        private SupportedEntity(@Nonnull Bits bits) {
            this.bits = bits;
        }

        @Override
        public Bits getBits() {
            return bits;
        }
    }

    public enum SupportedInteraction implements Bits.Wrapper {

        SUPPORT_RSP(Bits.forBitIndex(0, 7));
        private final Bits bits;

        private SupportedInteraction(@Nonnull Bits bits) {
            this.bits = bits;
        }

        @Override
        public Bits getBits() {
            return bits;
        }
    }
    private int oemDefined;
    private final Set<SupportedEntity> supportedEntities = EnumSet.noneOf(SupportedEntity.class);
    private final Set<SupportedInteraction> supportedInteractions = EnumSet.noneOf(SupportedInteraction.class);

    @Override
    public AsfRcmpMessageType getMessageType() {
        return AsfRcmpMessageType.PresencePong;
    }

    public int getOemDefined() {
        return oemDefined;
    }

    @Nonnull
    public AsfPresencePongData withOemDefined(int oemDefined) {
        this.oemDefined = oemDefined;
        return this;
    }

    @Nonnull
    public Set<? extends SupportedEntity> getSupportedEntities() {
        return supportedEntities;
    }

    @Nonnull
    public AsfPresencePongData withSupportedEntities(Iterable<? extends SupportedEntity> supportedEntities) {
        Iterables.addAll(this.supportedEntities, supportedEntities);
        return this;
    }

    @Nonnull
    public Set<? extends SupportedInteraction> getSupportedInteractions() {
        return supportedInteractions;
    }

    @Nonnull
    public AsfPresencePongData withSupportedInteractions(Iterable<? extends SupportedInteraction> supportedInteractions) {
        Iterables.addAll(this.supportedInteractions, supportedInteractions);
        return this;
    }

    @Override
    protected int getDataWireLength() {
        return 16;
    }

    @Override
    protected void toWireData(ByteBuffer buffer) {
        buffer.putInt(IANA_ENTERPRISE_NUMBER);
        buffer.putInt(getOemDefined());
        buffer.put(Bits.toByte(getSupportedEntities()));
        buffer.put(Bits.toByte(getSupportedInteractions()));
        buffer.put(new byte[6]);    // Reserved, zero.
    }

    @Override
    protected void fromWireData(ByteBuffer buffer) {
        assertWireInt(buffer, IANA_ENTERPRISE_NUMBER);
        withOemDefined(buffer.getInt());
        withSupportedEntities(Bits.fromBuffer(SupportedEntity.class, buffer, 1));
        withSupportedInteractions(Bits.fromBuffer(SupportedInteraction.class, buffer, 1));
        assertWireBytes(buffer, 0, 0, 0, 0, 0, 0);
    }
}
