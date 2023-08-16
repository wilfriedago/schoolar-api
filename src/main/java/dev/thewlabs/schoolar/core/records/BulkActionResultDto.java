package dev.thewlabs.schoolar.core.records;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BulkActionResultDto", description = "Bulk action result data transfer object.")
public record BulkActionResultDto(
        @Schema(description = "Flag indicating whether the bulk action was successful.")
        boolean success,

        @Schema(description = "Message describing the result of the bulk action.")
        String message,

        @Schema(description = "Number of actions that failed during the bulk action.")
        int failedActions,


        @Schema(description = "Number of actions that succeeded during the bulk action.")
        int succeedActions
) {
    public static BulkActionResultDto getResult(int initialActions, int finalResult) {
        int failedActions = initialActions - finalResult;
        boolean success = failedActions == 0;

        return new BulkActionResultDto(
                success,
                success ? "All records has been added successfully." : "Some records could not be added.",
                failedActions,
                finalResult
        );
    }
}
