package com.example.copd_asthma.features.predictionModel
//
//import org.tensorflow.lite.Interpreter
//import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
//import org.tensorflow.lite.support.tensorbuffer.TensorBufferFloat
//import java.nio.ByteBuffer
//import java.nio.ByteOrder
//
//fun predictCOPDSeverity(
//    age: Int,
//    smoking: Int,
//    packHistory: Float,
//    fev1: Float,
//    fvc: Float,
//    cat: Int,
//    sgrq: Float,
//    gender: Int,
//    diabetes: Int,
//    hypertension: Int,
//    mwt1: Float,
//    mwt2: Float,
//    mwt1Best: Float
//): String {
//    // Load the TFLite model
//    val model = Interpreter(loadModelFile("COPD_SEVERITY_CHECKER.tflite"))
//
//    // Prepare input data
//    val fev1Fvc = fev1 / fvc
//    val copd = 0.49f
//    val input = floatArrayOf(
//        age.toFloat(), smoking.toFloat(), packHistory, fev1, fvc, fev1Fvc, cat.toFloat(),
//        sgrq, gender.toFloat(), diabetes.toFloat(), hypertension.toFloat(), mwt1, mwt2, mwt1Best, copd
//    )
//
//    // Create input tensor
//    val inputBuffer = TensorBufferFloat.createFixedSize(intArrayOf(1, 15))
//    inputBuffer.loadArray(input)
//
//    // Create output tensor
//    val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 4), DataType.FLOAT32)
//
//    // Run inference
//    model.run(inputBuffer.buffer, outputBuffer.buffer.rewind())
//
//    // Get the output
//    val outputArray = outputBuffer.floatArray
//    val severityIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
//
//    // Map the output to severity levels
//    val severityMapping = mapOf(0 to "Mild", 1 to "Moderate", 2 to "Severe", 3 to "Very Severe")
//    return severityMapping[severityIndex] ?: "Unknown"
//}
//
//private fun loadModelFile(modelPath: String): ByteBuffer {
//    val assetFileDescriptor = context.assets.openFd(modelPath)
//    val inputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
//    val fileChannel = inputStream.channel
//    val startOffset = assetFileDescriptor.startOffset
//    val declaredLength = assetFileDescriptor.declaredLength
//    return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength).apply {
//        order(ByteOrder.nativeOrder())
//    }
//}