/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.todo_backend.application.controller.api;

import com.example.todo_backend.application.dto.TaskRequest;
import com.example.todo_backend.application.dto.TasksDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-10T09:27:05.776178400+09:00[GMT+09:00]", comments = "Generator version: 7.5.0")
@Validated
@Tag(name = "tasks", description = "the tasks API")
public interface TasksApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /tasks : Create a new task
     *
     * @param taskRequest (required)
     * @return Task created (status code 201)
     */
    @Operation(operationId = "createTask", summary = "Create a new task", responses = {
            @ApiResponse(responseCode = "201", description = "Task created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
            })
    })
    @RequestMapping(method = RequestMethod.POST, value = "/tasks", produces = { "application/json" }, consumes = {
            "application/json" })

    default ResponseEntity<TasksDto> createTask(
            @Parameter(name = "TaskRequest", description = "", required = true) @Valid @RequestBody TaskRequest taskRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dueDate\" : \"2025-12-31\", \"description\" : \"Milk, eggs, bread\", \"id\" : 0, \"title\" : \"Buy groceries\", \"status\" : \"PENDING\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /tasks/{id} : Delete a task by ID
     *
     * @param id (required)
     * @return Task deleted (status code 204)
     *         or Task not found (status code 404)
     */
    @Operation(operationId = "deleteTask", summary = "Delete a task by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{id}")

    default ResponseEntity<Void> deleteTask(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /tasks : Get all tasks
     *
     * @return A list of tasks (status code 200)
     */
    @Operation(operationId = "getAllTasks", summary = "Get all tasks", responses = {
            @ApiResponse(responseCode = "200", description = "A list of tasks", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TasksDto.class)))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = { "application/json" })

    default ResponseEntity<List<TasksDto>> getAllTasks(

    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"dueDate\" : \"2025-12-31\", \"description\" : \"Milk, eggs, bread\", \"id\" : 0, \"title\" : \"Buy groceries\", \"status\" : \"PENDING\" }, { \"dueDate\" : \"2025-12-31\", \"description\" : \"Milk, eggs, bread\", \"id\" : 0, \"title\" : \"Buy groceries\", \"status\" : \"PENDING\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /tasks/{id} : Get a task by ID
     *
     * @param id (required)
     * @return A task (status code 200)
     *         or Task not found (status code 404)
     */
    @Operation(operationId = "getTaskById", summary = "Get a task by ID", responses = {
            @ApiResponse(responseCode = "200", description = "A task", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{id}", produces = { "application/json" })

    default ResponseEntity<TasksDto> getTaskById(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dueDate\" : \"2025-12-31\", \"description\" : \"Milk, eggs, bread\", \"id\" : 0, \"title\" : \"Buy groceries\", \"status\" : \"PENDING\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /tasks/{id} : Update a task by ID
     *
     * @param id          (required)
     * @param taskRequest (required)
     * @return Task updated (status code 200)
     *         or Task not found (status code 404)
     */
    @Operation(operationId = "updateTask", summary = "Update a task by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Task updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/tasks/{id}", produces = { "application/json" }, consumes = {
            "application/json" })

    default ResponseEntity<TasksDto> updateTask(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
            @Parameter(name = "TaskRequest", description = "", required = true) @Valid @RequestBody TaskRequest taskRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"dueDate\" : \"2025-12-31\", \"description\" : \"Milk, eggs, bread\", \"id\" : 0, \"title\" : \"Buy groceries\", \"status\" : \"PENDING\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
