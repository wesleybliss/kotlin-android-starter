package com.kotlinandroidstarter.app.api

class Resource<T> {
    
    companion object {
        
        val LOADING = -1
        val ERROR = 0
        val SUCCESS = 1
        
        fun <T> success(data: T): Resource<T> = Resource(SUCCESS, data, null)
        
        fun <T> error(msg: String,  data: T? = null): Resource<T> = Resource(ERROR, data, msg)
        
        fun <T> error(msg: String,  errorType: Int? = null): Resource<T> = Resource(ERROR, errorType, msg)
        
        fun <T> errorWithMisc(miscInfo: Any? = null): Resource<T> = Resource(ERROR, miscInfo)
        
        fun <T> error(errorType: Int? = null): Resource<T> = Resource(ERROR, errorType)
        
        fun <T> loading(data: T? = null): Resource<T> = Resource(LOADING, data, null)
        
    }

    val status: Int
    var message: String? = null
    var data: T? = null
    var errorType: Int? = null
    var miscInfo: Any? = null

    constructor(status: Int, data: T?, message: String?) {
        this.status = status
        this.data = data
        this.message = message
    }

    constructor(status: Int, errorType: Int?) {
        this.status = status
        this.errorType = errorType
    }

    constructor(status: Int, errorType: Int?, msg: String) {
        this.status = status
        this.errorType = errorType
        this.message = msg
    }

    constructor(status: Int, miscInfo: Any?) {
        this.status = status
        this.miscInfo = miscInfo
    }

    fun attachErrorType(errorType: Int?) {
        this.errorType = errorType
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val resource = other as Resource<*>?

        if (status != resource!!.status) return false

        if (if (message != null) message != resource.message else resource.message != null) return false

        return if (data != null) data == resource.data else resource.data == null

    }

    override fun toString(): String {
        return "Resource{" +
            "status=" + status +
            ", message='" + message + '\''.toString() +
            ", data=" + data +
            '}'.toString()
    }

}
