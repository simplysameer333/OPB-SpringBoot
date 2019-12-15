package com.banking.opb.domain;

import java.util.Date;
import java.util.List;

import org.joda.money.Money;

import com.banking.opb.OpbApplication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Transaction {

    private String id;

    @JsonProperty("other_account")
    private Account targetAccount;

    @JsonProperty("this_account")
    private Account ownAccount;
    
    @JsonProperty("details")
    private Details details;

    private Metadata metadata;

    @Data
    private class Details {
    	@JsonProperty("type")
        private String type;
    	@JsonProperty("description")
        private String description;

        @JsonProperty("posted")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OpbApplication.ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        private Date postedDate;

        @JsonProperty("completed")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OpbApplication.ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        private Date completedDate;

        @JsonProperty("new_balance")
        //@JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
        private Amount newBalance;

        @JsonProperty("value")
        //@JsonDeserialize(using = MoneyJson.MoneyDeserializer.class)
        private Amount value;
    }

    @Data
    public class Metadata {
        private String narrative;
        private List<Object> comments;
        private List<Tag> tags;
        private List<Image> images;

        //@JsonProperty("where")
        private Location location;
    }

    @Data
    @NoArgsConstructor
    public static class Tag {
        public Tag(String value) {
            this.value = value;
        }
        private String value;

        private String id;

        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = OpbApplication.ISO8601_TIMESTAMP_FORMAT, timezone = "UTC")
        //@JsonProperty("date")
        private Date createdAt;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag tag = (Tag) o;
            return id.equals(tag.id);
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + id.hashCode();
            return result;
        }
    }

    @Data
    public static class Image {
        //@JsonProperty("image_URL")
        private String imageUrl;
    }
}
